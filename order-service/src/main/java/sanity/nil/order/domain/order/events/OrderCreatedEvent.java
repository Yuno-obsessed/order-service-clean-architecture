package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;
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

    private String address;

    private String orderStatus;

    private String paymentMethod;

    private String paymentOption;

    private List<OrderProductCreate> products;

    private BigDecimal totalPrice;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(UUID id, UUID clientID, AddressVO address, PaymentMethod paymentMethod,
                             PaymentOption paymentOption, List<OrderProductCreate> products, BigDecimal totalPrice) {
        this.baseEvent = new BaseEvent("OrderCreated");
        this.id = id;
        this.clientID = clientID;
        OrderStatus status = OrderStatus.CREATED;
        this.orderStatus = status.getValue();
        this.paymentMethod = paymentMethod.getValue();
        this.paymentOption = paymentOption.getValue();
        this.products = products;
        this.address = address.getAddressVOString();
        this.totalPrice = totalPrice;
    }

    public OrderCreatedEvent(BaseEvent baseEvent, UUID id, UUID clientID, String address, String orderStatus,
                             String paymentMethod, String paymentOption, List<OrderProductCreate> products, BigDecimal totalPrice) {
        this.baseEvent = baseEvent;
        this.id = id;
        this.clientID = clientID;
        this.address = address;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.paymentOption = paymentOption;
        this.products = products;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
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
