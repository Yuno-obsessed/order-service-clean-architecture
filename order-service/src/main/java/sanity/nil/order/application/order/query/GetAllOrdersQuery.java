package sanity.nil.order.application.order.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryFilters;
import sanity.nil.order.application.order.interfaces.cache.OrderCacheReader;

import java.util.List;

@RequiredArgsConstructor
public class GetAllOrdersQuery {

    private final OrderCacheReader orderCacheReader;

    public List<OrderQueryDTO> handle(OrderQueryFilters orderQueryFilters) {
        return orderCacheReader.getAllOrders(orderQueryFilters);
    }
}
