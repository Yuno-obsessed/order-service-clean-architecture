package sanity.nil.order.application.order.interfaces.persistence;

import sanity.nil.order.application.order.dto.address.AddressDTO;

import java.util.UUID;

public interface AddressReader {

    AddressDTO getAddressDTOById(UUID id);
}
