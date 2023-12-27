package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.order.application.common.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.application.order.dto.command.UpdateOrderAddressCommandDTO;
import sanity.nil.order.application.order.dto.response.UpdatedOrderAddressDTO;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.order.interfaces.persistence.OrderReader;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.services.OrderService;

@RequiredArgsConstructor
public class UpdateOrderAddressCommand {

    private final OrderDAO orderDAO;
    private final OrderReader orderReader;
    private final OrderService orderService;
    private final OutboxDAO outboxDAO;
    private final IdentityProvider identityProvider;

    public UpdatedOrderAddressDTO handle(UpdateOrderAddressCommandDTO dto) {
        Identity identity = identityProvider.getCurrentIdentity();
        Order order = orderReader.getOrderById(dto.orderID, identity.userID);
        Address address = orderReader.getAddress(dto.addressID);
        order = orderService.updateAddress(order, address);
        outboxDAO.addEvents(order.pullEvents());
        orderDAO.update(order);

        return new UpdatedOrderAddressDTO(order.getAddress().getAddressID().getId());
    }
}
