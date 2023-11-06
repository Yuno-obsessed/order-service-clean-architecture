package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.application.interfaces.broker.MessageBroker;
import sanity.nil.order.application.order.dto.boundary.OrderDTO;
import sanity.nil.order.application.order.dto.command.CreateOrderCommandDTO;
import sanity.nil.order.application.order.persistence.OrderDAO;
import sanity.nil.order.application.common.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.application.common.domain.event.Event;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateOrderCommand {

    private final OrderDAO orderDAO;
    private final OutboxDAO outboxDAO;
    private final OrderService orderService;
    private final MessageBroker messageBroker;

    public OrderDTO handle(CreateOrderCommandDTO dto) {
        List<UUID> productIDs = dto.products.stream().map(p -> p.productID).toList();
        List<OrderProduct> orderProductsRequested = dto.products.stream().map(p -> new OrderProduct(p.productID, p.quantity)).toList();
        List<OrderProduct> orderProducts = orderDAO.getProductsOfOrder(productIDs);
        Order order = orderService.create(dto.addressID, dto.userID, orderProducts, orderProductsRequested, dto.paymentMethod, dto.paymentOption);
        Order createdOrder = orderDAO.create(order);
        List<Event> events = order.pullEvents();
        outboxDAO.addEvents(events);
        for (Event event : events) {
            messageBroker.publishMessage(event.getExchange(), event.getRoute(), event.bytes());
        }
        return new OrderDTO(createdOrder.getOrderID().getId(), createdOrder.getClientID());
    }

}
