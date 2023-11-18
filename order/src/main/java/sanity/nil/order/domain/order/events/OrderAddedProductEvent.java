package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderAddedProductEvent implements Event {

    private BaseEvent baseEvent;

    private UUID orderID;

    private UUID clientID;

    private UUID productID;

    private String productName;

    private BigDecimal totalPrice;

    private int quantity;

    public OrderAddedProductEvent(UUID orderID, UUID clientID, UUID productID, String productName,
                                  BigDecimal totalPrice, int quantity) {
        this.baseEvent = new BaseEvent("OrderAddedProduct");
        this.orderID = orderID;
        this.clientID = clientID;
        this.productID = productID;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    @Override
    public byte[] bytes() {
        return Utils.getBytes(this);
    }

    @Override
    public UUID uniqueAggregateID() {
        return this.orderID;
    }

    @Override
    public BaseEvent getBaseEvent() {
        return baseEvent;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public UUID getClientID() {
        return clientID;
    }

    public UUID getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
