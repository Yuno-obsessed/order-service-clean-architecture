package sanity.nil.onlineshop.application.order.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;
import sanity.nil.onlineshop.application.order.interfaces.interactors.GetAddressInteractor;
import sanity.nil.onlineshop.application.order.interfaces.query.AddressReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetAddressInteractorImpl implements GetAddressInteractor {

    private final AddressReader addressReader;

    @Override
    public AddressDTO getAddressDTOById(UUID id) {
        return addressReader.getAddressDTOById(id);
    }
}
