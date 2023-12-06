package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.command.UpdateAddressCommandDTO;
import sanity.nil.order.application.order.interfaces.persistence.AddressDAO;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.services.AddressService;

@RequiredArgsConstructor
public class UpdateAddressCommand {

    private final AddressDAO addressDAO;
    private final AddressService service;

    public Address handle(UpdateAddressCommandDTO updateDTO) {
       Address address = service.updateAddress(updateDTO.addressID, updateDTO.country, updateDTO.city,
               updateDTO.streetName, updateDTO.buildingNumber, updateDTO.postalCode);
       return addressDAO.updateProduct(address);
    }
}
