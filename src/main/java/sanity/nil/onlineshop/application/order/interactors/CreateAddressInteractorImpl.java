package sanity.nil.onlineshop.application.order.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;
import sanity.nil.onlineshop.application.order.dto.address.CreateAddressDTO;
import sanity.nil.onlineshop.application.order.interfaces.interactors.CreateAddressInteractor;
import sanity.nil.onlineshop.application.order.interfaces.query.AddressDAO;
import sanity.nil.onlineshop.application.order.interfaces.query.AddressReader;
import sanity.nil.onlineshop.domain.order.entity.Address;
import sanity.nil.onlineshop.domain.order.service.AddressService;

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
