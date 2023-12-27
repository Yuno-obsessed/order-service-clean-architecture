package sanity.nil.order.application.order.interfaces.cache;

import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryFilters;

import java.util.List;
import java.util.UUID;

public interface OrderCacheReader {

    OrderQueryDTO getOrder(UUID clientID, UUID orderID);

    List<OrderQueryDTO> getOrdersOfClient(UUID clientID);

    List<OrderQueryDTO> getAllOrders(OrderQueryFilters queryFilters);

    List<OrderQueryDTO> getAllOrdersByUserID(UUID userID, OrderQueryFilters queryFilters);

}
