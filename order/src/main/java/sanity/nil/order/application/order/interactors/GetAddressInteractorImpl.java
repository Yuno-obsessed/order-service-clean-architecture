package sanity.nil.order.application.order.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.address.AddressDTO;
import sanity.nil.order.application.order.interfaces.interactors.GetAddressInteractor;
import sanity.nil.order.application.order.interfaces.persistence.AddressReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetAddressInteractorImpl implements GetAddressInteractor {

    private final AddressReader addressReader;

    @Override
    public AddressDTO getAddressDTOById(UUID id) {
        return addressReader.getAddressDTOById(id);
    }
}
