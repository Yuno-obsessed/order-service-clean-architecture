package sanity.nil.order.application.order.interfaces.cache;

import sanity.nil.order.application.order.dto.query.OrderQueryDTO;

import java.util.UUID;

public interface OrderCacheDAO {

    void saveOrder(OrderQueryDTO orderQueryDTO);

    void deleteOrder(UUID id);

}
