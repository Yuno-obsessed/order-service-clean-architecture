package sanity.nil.order.application.order.interfaces.interactors;


import sanity.nil.order.application.order.dto.address.AddressDTO;
import sanity.nil.order.application.order.dto.address.UpdateAddressDTO;

public interface UpdateAddressInteractor {

    AddressDTO update(UpdateAddressDTO updateDTO);
}
