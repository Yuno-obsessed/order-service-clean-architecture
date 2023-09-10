package sanity.nil.order.domain.order.aggregate;

import sanity.nil.order.domain.common.aggregate.BaseAggregate;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.events.OrderDeletedEvent;
import sanity.nil.order.domain.order.exceptions.OrderIsDeletedException;
import sanity.nil.order.domain.order.exceptions.OrderProductAlreadyIsContained;
import sanity.nil.order.domain.order.exceptions.OrderProductNotExists;
import sanity.nil.order.domain.order.exceptions.OrderWasDeliveredException;
import sanity.nil.order.domain.order.vo.OrderID;
import sanity.nil.order.domain.order.vo.OrderInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {

    private BaseAggregate aggregate;
    private OrderID orderID;
    private UUID addressID;
    private UUID clientID;
    private List<OrderProduct> products;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private PaymentOption paymentOption;
    private OrderInfo orderInfo;
    private BigDecimal totalPrice;
    private boolean closed;

    public void preprocessOrder() {
        if (orderInfo.isDeleted()) {
            throw OrderIsDeletedException.throwEx(orderInfo.getDeletedAt());
        }

        if (orderStatus.equals(OrderStatus.DELIVERED)) {
            throw OrderWasDeliveredException.throwEx();
        }
    }

    public void addProduct(OrderProduct product) {
        this.preprocessOrder();
        if (this.products.stream()
                .anyMatch(p -> p.getProductID()
                        .equals(product.getProductID()))) {
            throw OrderProductAlreadyIsContained.throwEx(product.getProductID());
        }
    }

    public void removeProduct(OrderProduct product) {
        this.preprocessOrder();
        if (!products.contains(product)) {
            throw OrderProductNotExists.throwEx(product.getProductID());
        }
        this.products.remove(product);
    }

    public void updateStatus(OrderStatus status) {
        this.preprocessOrder();
        this.orderStatus = status;
        if (status.equals(OrderStatus.DELIVERED) || status.equals(OrderStatus.CANCELED)) {
            this.closed = true;
        }
    }

    public void deleteOrder() {
        this.preprocessOrder();
        this.aggregate.recordEvent(new OrderDeletedEvent(this.orderID.getId()));
    }

    public Order(OrderID orderID, UUID addressID, UUID clientID, List<OrderProduct> products,
                 OrderStatus orderStatus, PaymentMethod paymentMethod, PaymentOption paymentOption) {
        this.orderID = orderID;
        this.addressID = addressID;
        this.clientID = clientID;
        this.products = products;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.paymentOption = paymentOption;
        this.orderInfo = new OrderInfo();
        this.closed = false;
    }

    public BaseAggregate getAggregate() {
        return aggregate;
    }

    public OrderID getOrderID() {
        return orderID;
    }

    public UUID getAddressID() {
        return addressID;
    }

    public UUID getClientID() {
        return clientID;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal totalDiscount = new BigDecimal(0);
        for (OrderProduct product : this.products) {
            totalPrice = totalPrice.add(product.getPrice());
            totalDiscount = totalDiscount.add(BigDecimal.valueOf(product.getDiscount()));
        }
        return totalPrice
                .subtract(totalDiscount
                        .divide(BigDecimal.valueOf(100))
                        .multiply(BigDecimal.valueOf(100))
                );
    }

    public boolean isClosed() {
        return closed;
    }
}
