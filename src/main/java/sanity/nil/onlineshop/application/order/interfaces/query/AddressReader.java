package sanity.nil.onlineshop.application.order.interfaces.query;

import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;

import java.util.UUID;

public interface AddressReader {

    AddressDTO getAddressDTOById(UUID id);
}
