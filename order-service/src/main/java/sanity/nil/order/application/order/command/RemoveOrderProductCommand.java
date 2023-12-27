package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.order.application.common.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.application.order.dto.command.RemoveOrderProductCommandDTO;
import sanity.nil.order.application.order.dto.response.RemovedOrderProductDTO;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.order.interfaces.persistence.OrderReader;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;

@RequiredArgsConstructor
public class RemoveOrderProductCommand {

    private final OrderDAO orderDAO;
    private final OrderReader orderReader;
    private final OrderService orderService;
    private final OutboxDAO outboxDAO;
    private final IdentityProvider identityProvider;

    public RemovedOrderProductDTO handle(RemoveOrderProductCommandDTO dto) {
        Identity identity = identityProvider.getCurrentIdentity();
        Order order = orderReader.getOrderById(dto.orderID, identity.userID);
        OrderProduct orderProduct = orderReader.getOrderProduct(dto.productID);
        order = orderService.removeProduct(order, orderProduct);
        outboxDAO.addEvents(order.pullEvents());
        orderDAO.update(order);

        return new RemovedOrderProductDTO(order.getOrderID().getId(),
                order.getClientID(), orderProduct.getId());
    }
}
