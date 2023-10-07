package sanity.nil.order.domain.order.events;

import sanity.nil.common.domain.Utils;
import sanity.nil.common.domain.event.BaseEvent;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderDeletedProductEvent implements OrderEvent {

    private BaseEvent baseEvent;

    private UUID id;

    private UUID clientID;

    private UUID productID;

    private BigDecimal totalPrice;

    private int quantity;

    public OrderDeletedProductEvent(UUID id, UUID clientID, UUID productID,
                                    BigDecimal totalPrice, int quantity) {
        baseEvent = new BaseEvent("OrderDeletedProduct");
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.totalPrice = totalPrice;
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
    public String getRouteAddition() {
        return "DeleteProduct";
    }

    @Override
    public int getStatus() {
        return 0;
    }

}
