package sanity.nil.order.application.interfaces.persistence;

import sanity.nil.order.application.dto.query.AddressQueryDTO;

import java.util.UUID;

public interface AddressReader {

    AddressQueryDTO getAddressQueryByID(UUID id);
}
