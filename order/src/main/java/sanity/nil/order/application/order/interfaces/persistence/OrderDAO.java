package sanity.nil.order.application.order.interfaces.persistence;

import sanity.nil.order.domain.order.aggregate.Order;

public interface OrderDAO {

    void create(Order order);
}
