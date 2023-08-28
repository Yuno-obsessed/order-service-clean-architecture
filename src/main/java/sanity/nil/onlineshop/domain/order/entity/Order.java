package sanity.nil.onlineshop.domain.order.entity;

import sanity.nil.onlineshop.domain.order.consts.OrderStatus;
import sanity.nil.onlineshop.domain.order.consts.PaymentMethod;
import sanity.nil.onlineshop.domain.order.consts.PaymentOption;
import sanity.nil.onlineshop.domain.order.vo.OrderID;

import java.util.List;
import java.util.UUID;

public class Order {

    private OrderID orderID;
    private UUID addressID;
    private UUID userID;
    private List<OrderProduct> products;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private PaymentOption paymentOption;

    public Order(OrderID orderID, UUID addressID, UUID userID, List<OrderProduct> products,
                 OrderStatus orderStatus, PaymentMethod paymentMethod, PaymentOption paymentOption) {
        this.orderID = orderID;
        this.addressID = addressID;
        this.userID = userID;
        this.products = products;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.paymentOption = paymentOption;
    }
}
