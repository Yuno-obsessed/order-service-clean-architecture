package sanity.nil.order.application.order.interfaces.interactors;


import sanity.nil.order.application.order.dto.address.AddressDTO;

import java.util.UUID;

public interface GetAddressInteractor {

    AddressDTO getAddressDTOById(UUID id);
}
