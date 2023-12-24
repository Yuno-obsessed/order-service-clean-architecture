package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;

import java.io.Serializable;
import java.util.UUID;

public class OrderUpdatedAddressEvent implements Event, Serializable {

    private BaseEvent baseEvent;

    private UUID orderID;

    private UUID clientID;

    private UUID addressID;

    private String country;

    private String city;

    private String streetName;

    private Integer buildingNumber;

    private String postalCode;

    public OrderUpdatedAddressEvent() {}

    public OrderUpdatedAddressEvent(UUID orderID, UUID clientID, UUID addressID, String country, String city,
                                    String streetName, Integer buildingNumber, String postalCode) {
        this.baseEvent = new BaseEvent("OrderUpdatedAddress");
        this.orderID = orderID;
        this.clientID = clientID;
        this.addressID = addressID;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
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

    public UUID getClientID() {
        return clientID;
    }

    public UUID getAddressID() {
        return addressID;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
