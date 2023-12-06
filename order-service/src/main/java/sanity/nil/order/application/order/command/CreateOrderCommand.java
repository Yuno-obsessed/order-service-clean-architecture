package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.interfaces.broker.MessageBroker;
import sanity.nil.order.application.common.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.application.order.dto.boundary.OrderDTO;
import sanity.nil.order.application.order.dto.command.CreateOrderCommandDTO;
import sanity.nil.order.application.order.exceptions.ProductQuantityMismatch;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.domain.common.event.Event;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.Address;
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
        Address address = orderDAO.getAddress(dto.addressID);
        List<OrderProduct> orderProducts = orderDAO.getProductsOfOrder(productIDs);
        for (OrderProduct product : orderProducts) {
            dto.products.stream()
                    .filter(p -> p.productID.equals(product.getProductID()))
                    .findFirst()
                    .ifPresent(p -> {
                        if (product.getQuantity() < p.getQuantity()) {
                            throw ProductQuantityMismatch.throwEx(product.getProductID(),
                                    product.getQuantity(), p.getQuantity());
                        } else {
                            product.setQuantity(p.getQuantity());
                        }
                    });
        }
        Order order = orderService.create(address, dto.userID, orderProducts, dto.paymentMethod, dto.paymentOption);
        Order createdOrder = orderDAO.create(order);
        List<Event> events = order.pullEvents();
        outboxDAO.addEvents(events);

        return new OrderDTO(createdOrder.getOrderID().getId(), createdOrder.getClientID());
    }

}
