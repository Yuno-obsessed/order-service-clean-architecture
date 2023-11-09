package sanity.nil.order.domain.order.events;

import sanity.nil.order.application.common.domain.Utils;
import sanity.nil.order.application.common.domain.event.BaseEvent;
import sanity.nil.order.application.common.domain.event.Event;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.vo.AddressVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderCreatedEvent implements Event, Serializable {

    private BaseEvent baseEvent;

    private UUID id;

    private UUID clientID;

    private AddressVO address;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    private PaymentOption paymentOption;

    private List<OrderProductCreate> products;

    private BigDecimal totalPrice;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(UUID id, UUID clientID, AddressVO address, PaymentMethod paymentMethod,
                             PaymentOption paymentOption, List<OrderProductCreate> products, BigDecimal totalPrice) {
        this.baseEvent = new BaseEvent("OrderCreated");
        this.id = id;
        this.clientID = clientID;
        this.orderStatus = OrderStatus.CREATED;
        this.paymentMethod = paymentMethod;
        this.paymentOption = paymentOption;
        this.products = products;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    @Override
    public byte[] bytes() {
        return Utils.getBytes(this);
    }

    @Override
    public UUID uniqueAggregateID() {
        return this.id;
    }

    @Override
    public BaseEvent getBaseEvent() {
        return baseEvent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientID() {
        return clientID;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public List<OrderProductCreate> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductCreate> products) {
        this.products = products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

}
