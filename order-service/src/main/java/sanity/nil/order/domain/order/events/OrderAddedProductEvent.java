package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderAddedProductEvent implements Event, Serializable {

    private BaseEvent baseEvent;

    private UUID id;

    private UUID clientID;

    private UUID productID;

    private String productName;

    private BigDecimal price;

    private int quantity;

    public OrderAddedProductEvent() {}

    public OrderAddedProductEvent(UUID id, UUID clientID, UUID productID, String productName,
                                  BigDecimal price, int quantity) {
        this.baseEvent = new BaseEvent("OrderAddedProduct");
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
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

    public UUID getClientID() {
        return clientID;
    }

    public UUID getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
