package sanity.nil.order.domain.order.entity;


import sanity.nil.order.domain.order.vo.AddressID;

import java.util.Objects;
import java.util.UUID;

public class Address {

    private AddressID addressID;
    private UUID userID;
    private String country;
    private String city;
    private String streetName;
    private Integer buildingNumber;
    private String postalCode;

    public Address(AddressID addressID, UUID userID, String country, String city, String streetName,
                   Integer buildingNumber, String postalCode) {
        this.addressID = addressID;
        this.userID = userID;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
    }

    public String getFullAddress() {
        StringBuilder sb = new StringBuilder()
                .append(country)
                .append(',')
                .append(city)
                .append(',')
                .append(streetName)
                .append(',')
                .append(buildingNumber)
                .append(',')
                .append(postalCode);
        return sb.toString();
    }

    public AddressID getAddressID() {
        return addressID;
    }

    public UUID getUserID() {
        return userID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(userID, address.userID) &&
                Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(streetName, address.streetName) &&
                Objects.equals(buildingNumber, address.buildingNumber) &&
                Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, country, city, streetName, buildingNumber, postalCode);
    }
}
