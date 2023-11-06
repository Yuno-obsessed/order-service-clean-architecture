package sanity.nil.order.domain.order.events;

import sanity.nil.order.application.common.domain.Utils;
import sanity.nil.order.application.common.domain.event.BaseEvent;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderAddedProductEvent implements OrderEvent {

    private BaseEvent baseEvent;

    private UUID orderID;

    private UUID clientID;

    private UUID productID;

    private String productName;

    private BigDecimal totalPrice;

    private int quantity;

    public OrderAddedProductEvent(UUID orderID, UUID clientID, UUID productID, String productName,
                                  BigDecimal totalPrice, int quantity) {
        this.baseEvent = new BaseEvent("OrderAddProduct");
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
    public String getRouteAddition() {
        return "AddProduct";
    }

    @Override
    public int getStatus() {
        return 0;
    }
}
