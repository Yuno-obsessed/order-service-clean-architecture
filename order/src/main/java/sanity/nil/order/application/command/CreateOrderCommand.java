package sanity.nil.order.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.common.application.interfaces.broker.MessageBroker;
import sanity.nil.order.application.dto.boundary.OrderDTO;
import sanity.nil.order.application.dto.command.CreateOrderDTO;
import sanity.nil.order.application.interfaces.persistence.OrderDAO;
import sanity.nil.common.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.common.domain.event.Event;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;

import java.util.List;

@RequiredArgsConstructor
public class CreateOrderCommand {

    private final OrderDAO orderDAO;
    private final OutboxDAO outboxDAO;
    private final OrderService orderService;
    private final MessageBroker messageBroker;

    public OrderDTO handle(CreateOrderDTO dto) {
        List<OrderProduct> orderProducts = orderDAO.getProductsOfOrder(dto.productIDs);
        Order order = orderService.create(dto.addressID, dto.userID, orderProducts, dto.paymentMethod, dto.paymentOption);
        Order createdOrder = orderDAO.create(order);
        List<Event> events = order.pullEvents();
        outboxDAO.addEvents(events);
        for (Event event : events) {
            messageBroker.publishMessage(event.getExchange(), event.getRoute(), event.bytes());
        }
        return new OrderDTO(createdOrder.getOrderID().getId(), createdOrder.getClientID());
    }

}
