package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.order.application.order.dto.command.UpdateAddressCommandDTO;
import sanity.nil.order.application.order.interfaces.persistence.AddressDAO;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.services.AddressService;

@RequiredArgsConstructor
public class UpdateAddressCommand {

    private final AddressDAO addressDAO;
    private final AddressService service;
    private final IdentityProvider identityProvider;

    public Address handle(UpdateAddressCommandDTO updateDTO) {
        Identity identity = identityProvider.getCurrentIdentity();
       Address address = service.updateAddress(updateDTO.addressID, identity.userID, updateDTO.country, updateDTO.city,
               updateDTO.streetName, updateDTO.buildingNumber, updateDTO.postalCode);
       return addressDAO.updateAddress(address);
    }
}
