package sanity.nil.order.application.order.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.command.*;

@RequiredArgsConstructor
public class OrderCommandService {

    public final CreateOrderCommand createOrderCommand;
    public final AddOrderProductCommand addOrderProductCommand;
    public final UpdateProductQuantityCommand updateProductQuantityCommand;
    public final RemoveOrderProductCommand removeOrderProductCommand;
    public final UpdateOrderAddressCommand updateOrderAddressCommand;
}
