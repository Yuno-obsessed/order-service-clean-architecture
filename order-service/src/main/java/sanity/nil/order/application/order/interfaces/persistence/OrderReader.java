package sanity.nil.order.application.order.interfaces.persistence;

import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.entity.OrderProduct;

import java.util.List;
import java.util.UUID;

public interface OrderReader {

    List<OrderProduct> getProductsOfOrder(List<UUID> ids);

    OrderProduct getOrderProduct(UUID id);

    Order getOrderById(UUID orderID, UUID userID);

    Address getAddress(UUID id);

}
