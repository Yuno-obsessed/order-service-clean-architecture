package sanity.nil.order.application.order.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.CreateOrderDTO;
import sanity.nil.order.application.order.dto.OrderCreatedDTO;
import sanity.nil.order.application.order.interfaces.interactors.CreateOrderInteractor;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.domain.common.event.Event;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;
import sanity.nil.order.infrastructure.messageBroker.interfaces.BrokerTemplate;

import java.util.List;

@RequiredArgsConstructor
public class CreateOrderInteractorImpl implements CreateOrderInteractor {

    private final OrderDAO orderDAO;
    private final OutboxDAO outboxDAO;
    private final OrderService orderService;
    private final BrokerTemplate brokerTemplate;

    @Override
    public OrderCreatedDTO create(CreateOrderDTO dto) {
        List<OrderProduct> orderProducts = orderDAO.getProductsOfOrder(dto.productIDs);
        Order order = orderService.create(dto.addressID, dto.userID, orderProducts, dto.paymentMethod, dto.paymentOption);
        Order createdOrder = orderDAO.create(order);
        List<Event> events = order.pullEvents();
        outboxDAO.addEvents(events);
        for (Event event : events) {
            brokerTemplate.publishMessage(event.bytes());
        }
        return new OrderCreatedDTO(createdOrder.getOrderID().getId(), createdOrder.getClientID());
    }

}
