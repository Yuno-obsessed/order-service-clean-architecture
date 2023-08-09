package sanity.nil.onlineshop.domain.address.service;

import sanity.nil.onlineshop.domain.address.entity.Address;
import sanity.nil.onlineshop.domain.address.vo.AddressID;

public class AddressService {

    public Address createAddress(String country, String city, String streetName,
                                 Integer buildingNumber, String postalCode) {
        return new Address(new AddressID(), country, city, streetName, buildingNumber, postalCode);
    }
}
