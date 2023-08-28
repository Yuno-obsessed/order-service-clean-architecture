package sanity.nil.onlineshop.application.order.interfaces.interactors;

import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;
import sanity.nil.onlineshop.application.order.dto.address.CreateAddressDTO;

public interface CreateAddressInteractor {

    AddressDTO create(CreateAddressDTO createDTO);
}
