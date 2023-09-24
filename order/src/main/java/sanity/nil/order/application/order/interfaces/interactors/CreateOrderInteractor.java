package sanity.nil.order.application.order.interfaces.interactors;

import sanity.nil.order.application.order.dto.CreateOrderDTO;
import sanity.nil.order.application.order.dto.OrderCreatedDTO;

public interface CreateOrderInteractor {

    OrderCreatedDTO create(CreateOrderDTO createOrderDTO);
}
