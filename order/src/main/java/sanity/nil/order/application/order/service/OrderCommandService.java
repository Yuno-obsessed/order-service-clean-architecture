package sanity.nil.order.application.order.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.command.CreateOrderCommand;

@RequiredArgsConstructor
public class OrderCommandService {

    public final CreateOrderCommand createOrderCommand;
}
