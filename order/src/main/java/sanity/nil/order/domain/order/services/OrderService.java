package sanity.nil.order.domain.order.services;


import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.events.*;
import sanity.nil.order.domain.order.exceptions.OrderProductsEmptyException;
import sanity.nil.order.domain.order.exceptions.ProductQuantityIsLessException;
import sanity.nil.order.domain.order.exceptions.RequestedProductNoMatchException;
import sanity.nil.order.domain.order.vo.OrderID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderService {

    public Order create(UUID addressID, UUID userID, List<OrderProduct> products, List<OrderProduct> requestedProducts,
                        String paymentMethod, String paymentOption) {
        if (products == null || products.isEmpty()){
            throw new OrderProductsEmptyException();
        }

        for (OrderProduct requestedProduct : requestedProducts) {
           OrderProduct product =
                   products.stream()
                           .filter(p -> p.getProductID().equals(requestedProduct.getProductID()))
                           .findFirst().orElseThrow(
                                   () -> RequestedProductNoMatchException.throwEx(requestedProduct.getProductID())
                           );
           if (product.getQuantity() < requestedProduct.getQuantity()) {
               throw ProductQuantityIsLessException.throwEx(requestedProduct.getProductID());
           }
        }

        Order createdOrder = new Order(new OrderID(), addressID, userID, products, OrderStatus.CREATED,
                PaymentMethod.valueOf(paymentMethod), PaymentOption.valueOf(paymentOption));


        List<OrderProductCreate> productEvents = new ArrayList<>();
        for (OrderProduct product : products) {
            OrderProductCreate orderProduct =
                    new OrderProductCreate(product.getProductID(), product.getName(), product.getPrice());
            productEvents.add(orderProduct);
        }

        createdOrder.recordEvent(
                new OrderCreatedEvent(createdOrder.getOrderID().getId(),
                        createdOrder.getClientID(), createdOrder.getAddressID(),
                        createdOrder.getPaymentMethod(), createdOrder.getPaymentOption(),
                        productEvents, createdOrder.getTotalPrice())
        );

        return createdOrder;
    }

    public Order update(Order order, UUID addressID, UUID userID, List<OrderProduct> products) {
//                        String paymentMethod, String paymentOption) {
        if (products == null || products.isEmpty()){
            throw new OrderProductsEmptyException();
        }

        Order updatedOrder = order;
        if (order.getAddressID() != addressID) {
            updatedOrder.setAddressID(addressID);
            updatedOrder.recordEvent(
                    new OrderChangedAddressEvent(order.getOrderID().getId(), addressID)
            );
        }

        Map<OrderProduct, Integer> deletedProducts = order.getMatchingProducts(products, order.getProducts());
        if (!deletedProducts.isEmpty()) {
            for (OrderProduct product : deletedProducts.keySet()) {
                updatedOrder.removeProduct(product);
                updatedOrder.recordEvent(
                        new OrderDeletedProductEvent(order.getOrderID().getId(), order.getClientID(),
                                product.getProductID(), product.getTotalPrice(), deletedProducts.get(product))
                );
            }
        }

        Map<OrderProduct, Integer> addedProducts = order.getMatchingProducts(order.getProducts(), products);
        if (!addedProducts.isEmpty()) {
            for (OrderProduct product : addedProducts.keySet()) {
                updatedOrder.addProduct(product);
                updatedOrder.recordEvent(
                        new OrderAddedProductEvent(order.getOrderID().getId(), order.getClientID(),
                                product.getProductID(), product.getName(), product.getTotalPrice(), addedProducts.get(product))
                );
            }
        }

        return new Order(order.getOrderID(), addressID, userID, products, OrderStatus.CREATED,
                order.getPaymentMethod(), order.getPaymentOption());
    }
}
