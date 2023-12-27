package sanity.nil.order.domain.order.services;

import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.vo.AddressID;

import java.util.UUID;

public class AddressService {

    public Address createAddress(UUID userID, String country, String city, String streetName,
                                 Integer buildingNumber, String postalCode) {
        return new Address(new AddressID(), userID, country, city, streetName, buildingNumber, postalCode);
    }

    public Address updateAddress(UUID addressID, UUID userID, String country, String city,
                                 String streetName, Integer buildingNumber, String postalCode) {
        return new Address(new AddressID(addressID), userID, country, city, streetName, buildingNumber, postalCode);
    }
}
