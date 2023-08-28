package sanity.nil.onlineshop.application.order.interfaces.interactors;

import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;
import sanity.nil.onlineshop.application.order.dto.address.UpdateAddressDTO;

public interface UpdateAddressInteractor {

    AddressDTO update(UpdateAddressDTO updateDTO);
}
