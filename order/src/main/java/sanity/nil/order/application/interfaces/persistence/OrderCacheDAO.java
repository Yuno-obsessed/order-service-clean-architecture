package sanity.nil.order.application.interfaces.persistence;

import sanity.nil.order.application.dto.query.OrderQueryDTO;

import java.util.List;
import java.util.UUID;

public interface OrderCacheDAO {

    OrderQueryDTO getOrder(UUID id);

    List<OrderQueryDTO> getOrders();

    void saveOrder(OrderQueryDTO orderQueryDTO);

    void deleteOrder(UUID id);

}
