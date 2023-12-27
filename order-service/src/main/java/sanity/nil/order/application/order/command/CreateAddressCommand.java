package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.order.application.order.dto.command.CreateAddressDTO;
import sanity.nil.order.application.order.interfaces.persistence.AddressDAO;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.services.AddressService;

@RequiredArgsConstructor
public class CreateAddressCommand {

    private final AddressDAO addressDAO;
    private final AddressService service;
    private final IdentityProvider identityProvider;

    public Address handle(CreateAddressDTO createDTO) {
        Identity identity = identityProvider.getCurrentIdentity();
        Address address = service.createAddress(identity.userID, createDTO.country, createDTO.city,
                createDTO.streetName, createDTO.buildingNumber, createDTO.postalCode);
        return addressDAO.createAddress(address);
    }
}
