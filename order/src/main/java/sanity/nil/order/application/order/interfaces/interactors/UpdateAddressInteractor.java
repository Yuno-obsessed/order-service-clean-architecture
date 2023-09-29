package sanity.nil.order.application.order.interfaces.interactors;


import sanity.nil.order.application.order.dto.address.AddressCreatedDTO;
import sanity.nil.order.application.order.dto.address.UpdateAddressDTO;

public interface UpdateAddressInteractor {

    AddressCreatedDTO update(UpdateAddressDTO updateDTO);
}
