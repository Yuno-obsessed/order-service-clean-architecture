package sanity.nil.order.application.order.interfaces.cache;

import sanity.nil.order.application.order.dto.query.OrderQueryDTO;

import java.util.UUID;

public interface OrderCacheDAO {

    void saveOrder(OrderQueryDTO orderQueryDTO);

    OrderQueryDTO deleteOrder(UUID clientID, UUID orderID);

}
