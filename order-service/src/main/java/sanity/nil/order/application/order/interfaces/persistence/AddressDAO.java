package sanity.nil.order.application.order.interfaces.persistence;


import sanity.nil.order.domain.order.entity.Address;

public interface AddressDAO {

    Address createAddress(Address address);

    Address updateAddress(Address address);

}
