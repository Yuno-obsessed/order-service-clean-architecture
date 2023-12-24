package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderUpdatedProductQuantityEvent implements Event, Serializable {

    private BaseEvent baseEvent;

    private UUID id;

    private UUID clientID;

    private UUID productID;

    private int quantity;

    private int updatedQuantity;

    private BigDecimal updatedOrderPrice;

    public OrderUpdatedProductQuantityEvent() {}

    public OrderUpdatedProductQuantityEvent(UUID id, UUID clientID, UUID productID, int quantity, int updatedQuantity,
                                            BigDecimal updatedOrderPrice) {
        this.baseEvent = new BaseEvent("OrderUpdatedProductQuantity");
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
        this.updatedQuantity = updatedQuantity;
        this.updatedOrderPrice = updatedOrderPrice;
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

    public UUID getProductID() {
        return productID;
    }

    public UUID getClientID() {
        return clientID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUpdatedQuantity() {
        return updatedQuantity;
    }

    public BigDecimal getUpdatedOrderPrice() {
        return updatedOrderPrice;
    }

}
