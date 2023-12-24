package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.application.order.dto.command.UpdateOrderProductQuantityCommandDTO;
import sanity.nil.order.application.order.dto.response.UpdatedOrderProductQuantityDTO;
import sanity.nil.order.application.order.exceptions.ProductQuantityMismatch;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.order.interfaces.persistence.OrderReader;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;

@RequiredArgsConstructor
public class UpdateProductQuantityCommand {

    private final OrderDAO orderDAO;
    private final OrderReader orderReader;
    private final OrderService orderService;
    private final OutboxDAO outboxDAO;

    public UpdatedOrderProductQuantityDTO handle(UpdateOrderProductQuantityCommandDTO dto) {
        Order order = orderReader.getOrderById(dto.orderID);
        OrderProduct orderProduct = orderReader.getOrderProduct(dto.productID);
        if (orderProduct.getQuantity() < dto.quantity) {
            throw ProductQuantityMismatch
                    .throwEx(orderProduct.getId(), orderProduct.getQuantity(), dto.quantity);
        }
        order = orderService.setProductQuantity(order, orderProduct, dto.quantity);
        outboxDAO.addEvents(order.pullEvents());
        order = orderDAO.update(order);

        return new UpdatedOrderProductQuantityDTO(order.getOrderID().getId(), orderProduct.getId(), orderProduct.getQuantity());
    }
}
