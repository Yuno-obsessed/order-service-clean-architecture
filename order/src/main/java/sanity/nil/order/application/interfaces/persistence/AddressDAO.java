package sanity.nil.order.application.interfaces.persistence;


import sanity.nil.order.domain.order.entity.Address;

public interface AddressDAO {

    Address createAddress(Address address);

    Address updateProduct(Address address);

}
