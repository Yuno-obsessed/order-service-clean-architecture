package sanity.nil.order.application.order.interfaces.interactors;


import sanity.nil.order.application.order.dto.address.AddressCreatedDTO;
import sanity.nil.order.application.order.dto.address.AddressDTO;
import sanity.nil.order.application.order.dto.address.CreateAddressDTO;

public interface CreateAddressInteractor {

    AddressCreatedDTO create(CreateAddressDTO createDTO);
}
