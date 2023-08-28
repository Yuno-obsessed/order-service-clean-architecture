package sanity.nil.onlineshop.domain.order.service;

import sanity.nil.onlineshop.domain.order.entity.Address;
import sanity.nil.onlineshop.domain.order.vo.AddressID;

import java.util.UUID;

public class AddressService {

    public Address createAddress(String country, String city, String streetName,
                                 Integer buildingNumber, String postalCode) {
        return new Address(new AddressID(), country, city, streetName, buildingNumber, postalCode);
    }

    public Address updateAddress(UUID addressID, String country, String city,
                                 String streetName, Integer buildingNumber, String postalCode) {
        return new Address(new AddressID(addressID), country, city, streetName, buildingNumber, postalCode);
    }
}
