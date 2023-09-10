package sanity.nil.order.application.order.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.address.AddressDTO;
import sanity.nil.order.application.order.dto.address.CreateAddressDTO;
import sanity.nil.order.application.order.interfaces.interactors.CreateAddressInteractor;
import sanity.nil.order.application.order.interfaces.persistence.AddressDAO;
import sanity.nil.order.application.order.interfaces.persistence.AddressReader;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.services.AddressService;

@RequiredArgsConstructor
public class CreateAddressInteractorImpl implements CreateAddressInteractor {

    private final AddressDAO addressDAO;
    private final AddressReader addressReader;
    private final AddressService service;

    @Override
    public AddressDTO create(CreateAddressDTO createDTO) {
        Address address = service.createAddress(createDTO.country, createDTO.city,
                createDTO.streetName, createDTO.buildingNumber, createDTO.postalCode);
        address = addressDAO.createAddress(address);
        return addressReader.getAddressDTOById(address.getAddressID().getId());
    }
}
