package sanity.nil.order.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.dto.query.AddressQueryDTO;
import sanity.nil.order.application.interfaces.persistence.AddressReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetAddressQuery {

    private final AddressReader addressReader;

    public AddressQueryDTO handle(UUID id) {
        return addressReader.getAddressQueryByID(id);
    }
}
