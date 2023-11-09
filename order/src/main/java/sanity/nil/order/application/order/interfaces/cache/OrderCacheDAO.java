package sanity.nil.order.application.order.interfaces.cache;

import sanity.nil.order.application.order.dto.query.OrderQueryDTO;

import java.util.List;
import java.util.UUID;

public interface OrderCacheDAO {

    OrderQueryDTO getOrder(UUID id);

    List<OrderQueryDTO> getOrdersOfClient(UUID clientID);

    void saveOrder(OrderQueryDTO orderQueryDTO);

    void deleteOrder(UUID id);

}
