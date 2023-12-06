package sanity.nil.order.application.order.interfaces.persistence;

import sanity.nil.order.application.order.dto.query.AddressQueryDTO;

import java.util.UUID;

public interface AddressReader {

    AddressQueryDTO getAddressQueryByID(UUID id);
}
