package sanity.nil.onlineshop.application.order.interfaces.interactors;

import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;
import sanity.nil.onlineshop.application.order.interfaces.query.AddressReader;

import java.util.UUID;

public interface GetAddressInteractor {

    AddressDTO getAddressDTOById(UUID id);
}
