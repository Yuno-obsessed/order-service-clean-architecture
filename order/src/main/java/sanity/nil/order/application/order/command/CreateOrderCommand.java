package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.boundary.OrderDTO;
import sanity.nil.order.application.order.dto.command.CreateOrderDTO;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.domain.common.event.Event;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;
import sanity.nil.order.infrastructure.messageBroker.interfaces.BrokerTemplate;

import java.util.List;

@RequiredArgsConstructor
public class CreateOrderCommand {

    private final OrderDAO orderDAO;
    private final OutboxDAO outboxDAO;
    private final OrderService orderService;
    private final BrokerTemplate brokerTemplate;

    public OrderDTO handle(CreateOrderDTO dto) {
        List<OrderProduct> orderProducts = orderDAO.getProductsOfOrder(dto.productIDs);
        Order order = orderService.create(dto.addressID, dto.userID, orderProducts, dto.paymentMethod, dto.paymentOption);
        Order createdOrder = orderDAO.create(order);
        List<Event> events = order.pullEvents();
        outboxDAO.addEvents(events);
        for (Event event : events) {
            brokerTemplate.publishMessage(event.bytes());
        }
        return new OrderDTO(createdOrder.getOrderID().getId(), createdOrder.getClientID());
    }

}
