package sanity.nil.order.application.order.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.query.AddressQueryDTO;
import sanity.nil.order.application.order.interfaces.persistence.AddressReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetAddressQuery {

    private final AddressReader addressReader;

    public AddressQueryDTO handle(UUID id) {
        return addressReader.getAddressQueryByID(id);
    }
}
