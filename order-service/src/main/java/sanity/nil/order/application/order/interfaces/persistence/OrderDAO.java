package sanity.nil.order.application.order.interfaces.persistence;

import sanity.nil.order.domain.order.aggregate.Order;

public interface OrderDAO {

    Order create(Order order);

    Order update(Order order);
}
