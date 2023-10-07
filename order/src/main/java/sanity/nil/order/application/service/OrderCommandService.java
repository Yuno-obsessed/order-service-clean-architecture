package sanity.nil.order.application.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.command.CreateOrderCommand;

@RequiredArgsConstructor
public class OrderCommandService {

    public final CreateOrderCommand createOrderCommand;
}
