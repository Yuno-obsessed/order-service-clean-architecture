package sanity.nil.order.domain.order.services;


import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.events.*;
import sanity.nil.order.domain.order.exceptions.OrderProductsEmptyException;
import sanity.nil.order.domain.order.exceptions.ProductNotInOrderException;
import sanity.nil.order.domain.order.vo.AddressVO;
import sanity.nil.order.domain.order.vo.OrderID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {

    public Order create(Address address, UUID userID, List<OrderProduct> products,
                        String paymentMethod, String paymentOption) {
        if (products == null || products.isEmpty()){
            throw new OrderProductsEmptyException();
        }

        Order createdOrder = new Order(new OrderID(), address, userID, products, OrderStatus.CREATED,
                PaymentMethod.valueOf(paymentMethod), PaymentOption.valueOf(paymentOption));


        List<OrderProductCreate> productEvents = new ArrayList<>();
        for (OrderProduct product : products) {
            OrderProductCreate orderProduct =
                    new OrderProductCreate(product.getId(), product.getName(),
                            product.getTotalPrice(), product.getQuantity());
            productEvents.add(orderProduct);
            createdOrder.recordEvent(
                    new OrderProductReservedEvent(product.getId(),
                            product.getQuantity())
            );
        }

        createdOrder.recordEvent(
                new OrderCreatedEvent(createdOrder.getOrderID().getId(),
                        createdOrder.getClientID(), new AddressVO(address.getAddressID().getId(), userID,
                        address.getFullAddress()), createdOrder.getPaymentMethod(), createdOrder.getPaymentOption(),
                        productEvents, createdOrder.getTotalPrice())
        );

        return createdOrder;
    }

    public Order addProduct(Order order, OrderProduct product) {
        Order updatedOrder = order;

        updatedOrder.addProduct(product);
        updatedOrder.recordEvent(new OrderAddedProductEvent(
                updatedOrder.getOrderID().getId(), updatedOrder.getClientID(), product.getId(),
                product.getName(), product.getPrice(), product.getQuantity()
        ));

        return updatedOrder;
    }

    public Order removeProduct(Order order, OrderProduct product) {
        Order updatedOrder = order;

        updatedOrder.removeProduct(product);
        updatedOrder.recordEvent(new OrderRemovedProductEvent(
                updatedOrder.getOrderID().getId(), updatedOrder.getClientID(), product.getId(),
                product.getTotalPrice()
        ));
        updatedOrder.recordEvent(new OrderProductReleasedEvent(
               product.getId(), product.getQuantity()
        ));

        return updatedOrder;
    }

    public Order updateAddress(Order order, Address address) {
        Order updatedOrder = order;
        if (!order.getAddress().equals(address)) {
            updatedOrder.setAddress(address);
            updatedOrder.recordEvent(new OrderUpdatedAddressEvent(
                    order.getOrderID().getId(), order.getClientID(), address.getAddressID().getId(),
                    address.getCountry(), address.getCity(), address.getStreetName(), address.getBuildingNumber(),
                    address.getPostalCode()
            ));
        }

        return updatedOrder;
    }

    public Order setProductQuantity(Order order, OrderProduct orderProduct, int quantity) {
        Order updatedOrder = order;

        int currentQuantity = orderProduct.getQuantity();
        OrderProduct product = updatedOrder.getProducts().stream()
                .filter(e -> e.equals(orderProduct))
                .findFirst()
                .orElseThrow(() -> new ProductNotInOrderException(orderProduct.getId()));
        product.setQuantity(quantity);
        updatedOrder.recordEvent(new OrderUpdatedProductQuantityEvent(
                updatedOrder.getOrderID().getId(), updatedOrder.getClientID(),
                orderProduct.getId(), currentQuantity, product.getQuantity(),
                order.getTotalPrice()
        ));

        return updatedOrder;
    }

    public Order cancel(Order order) {
        order.updateStatus(OrderStatus.CANCELED);

//        order.recordEvent();
        return order;
    }
}
