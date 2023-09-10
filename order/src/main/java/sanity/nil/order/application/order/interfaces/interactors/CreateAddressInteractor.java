package sanity.nil.order.application.order.interfaces.interactors;


import sanity.nil.order.application.order.dto.address.AddressDTO;
import sanity.nil.order.application.order.dto.address.CreateAddressDTO;

public interface CreateAddressInteractor {

    AddressDTO create(CreateAddressDTO createDTO);
}
