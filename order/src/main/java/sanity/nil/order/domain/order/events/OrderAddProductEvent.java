package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;
import sanity.nil.order.domain.order.vo.OrderID;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderAddProductEvent implements Event {

    private BaseEvent baseEvent;

    private OrderID orderID;

    private UUID clientID;

    private UUID productID;

    private String productName;

    private BigDecimal productPrice;

    private BigDecimal totalPrice;

    public OrderAddProductEvent(OrderID orderID, UUID clientID, UUID productID, String productName,
                                BigDecimal productPrice, BigDecimal totalPrice) {
        this.baseEvent = new BaseEvent("OrderAddProduct");
        this.orderID = orderID;
        this.clientID = clientID;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
    }

    @Override
    public byte[] bytes() {
        return Utils.getBytes(this);
    }

    @Override
    public UUID uniqueAggregateID() {
        return this.orderID.getId();
    }
}
