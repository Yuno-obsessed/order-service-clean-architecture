package sanity.nil.onlineshop.application.order.interfaces.query;

import sanity.nil.onlineshop.domain.order.entity.Address;

import java.util.UUID;

public interface AddressDAO {

    Address createAddress(Address address);

    Address updateProduct(Address address);

}
