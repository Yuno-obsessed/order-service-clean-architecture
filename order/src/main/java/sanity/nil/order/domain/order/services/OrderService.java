package sanity.nil.order.domain.order.services;


import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.events.OrderCreatedEvent;
import sanity.nil.order.domain.order.events.OrderProductCreate;
import sanity.nil.order.domain.order.exceptions.OrderProductsEmptyException;
import sanity.nil.order.domain.order.vo.OrderID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {

    public Order create(UUID addressID, UUID userID, List<OrderProduct> products,
                        String paymentMethod, String paymentOption) {
        Order createdOrder = new Order(new OrderID(), addressID, userID, products, OrderStatus.CREATED,
                PaymentMethod.valueOf(paymentMethod), PaymentOption.valueOf(paymentOption));
        if (products == null || products.isEmpty()){
            throw new OrderProductsEmptyException();
        }

        List<OrderProductCreate> productEvents = new ArrayList<>();
        for (OrderProduct product : products) {
            OrderProductCreate orderProduct =
                    new OrderProductCreate(product.getProductID(), product.getName(), product.getPrice());
            productEvents.add(orderProduct);
        }

        createdOrder.getAggregate().recordEvent(
                new OrderCreatedEvent(createdOrder.getOrderID().getId(),
                        createdOrder.getClientID(), createdOrder.getAddressID(),
                        createdOrder.getPaymentMethod(), createdOrder.getPaymentOption(),
                        productEvents, createdOrder.getTotalPrice()));

        return createdOrder;
    }

    public Order update(UUID orderID, UUID addressID, UUID userID, List<OrderProduct> products,
                        String paymentMethod, String paymentOption) {

        return new Order(new OrderID(orderID), addressID, userID, products, OrderStatus.CREATED,
                PaymentMethod.valueOf(paymentMethod), PaymentOption.valueOf(paymentOption));
    }
}
