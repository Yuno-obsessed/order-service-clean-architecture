package sanity.nil.onlineshop.domain.address.entity;

import sanity.nil.onlineshop.domain.address.vo.AddressID;

public class Address {

    private AddressID addressID;
    private String country;
    private String city;
    private String streetName;
    private Integer buildingNumber;
    private String postalCode;

    public Address(AddressID addressID, String country, String city, String streetName,
                   Integer buildingNumber, String postalCode) {
        this.addressID = addressID;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
    }


}
