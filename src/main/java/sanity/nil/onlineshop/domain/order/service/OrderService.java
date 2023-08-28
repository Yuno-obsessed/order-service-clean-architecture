package sanity.nil.onlineshop.domain.order.service;

import sanity.nil.onlineshop.domain.order.consts.OrderStatus;
import sanity.nil.onlineshop.domain.order.consts.PaymentMethod;
import sanity.nil.onlineshop.domain.order.consts.PaymentOption;
import sanity.nil.onlineshop.domain.order.entity.Order;
import sanity.nil.onlineshop.domain.order.entity.OrderProduct;
import sanity.nil.onlineshop.domain.order.vo.OrderID;

import java.util.List;
import java.util.UUID;

public class OrderService {

    public Order create(UUID addressID, UUID userID, List<OrderProduct> products,
                        String paymentMethod, String paymentOption) {

        return new Order(new OrderID(), addressID, userID, products, OrderStatus.CREATED,
                PaymentMethod.valueOf(paymentMethod), PaymentOption.valueOf(paymentOption));
    }

    public Order update(UUID orderID, UUID addressID, UUID userID, List<OrderProduct> products,
                        String paymentMethod, String paymentOption) {

        return new Order(new OrderID(orderID), addressID, userID, products, OrderStatus.CREATED,
                PaymentMethod.valueOf(paymentMethod), PaymentOption.valueOf(paymentOption));
    }
}
