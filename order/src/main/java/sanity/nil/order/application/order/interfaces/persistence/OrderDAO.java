package sanity.nil.order.application.order.interfaces.persistence;

import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.entity.OrderProduct;

import java.util.List;
import java.util.UUID;

public interface OrderDAO {

    Order create(Order order);

    List<OrderProduct> getProductsOfOrder(List<UUID> ids);

    // address serves dao or not (use no domain, only application)
}
