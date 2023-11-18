package sanity.nil.mailservice.domain.events;

import sanity.nil.mailservice.domain.common.BaseEvent;
import sanity.nil.mailservice.domain.common.Event;

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

    public OrderCreatedEvent() {
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
        return null;
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

