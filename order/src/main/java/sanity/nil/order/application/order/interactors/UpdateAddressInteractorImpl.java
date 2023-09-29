package sanity.nil.order.application.order.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.address.AddressCreatedDTO;
import sanity.nil.order.application.order.dto.address.UpdateAddressDTO;
import sanity.nil.order.application.order.interfaces.interactors.UpdateAddressInteractor;
import sanity.nil.order.application.order.interfaces.persistence.AddressDAO;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.services.AddressService;

@RequiredArgsConstructor
public class UpdateAddressInteractorImpl implements UpdateAddressInteractor {

    private final AddressDAO addressDAO;
    private final AddressService service;

    @Override
    public AddressCreatedDTO update(UpdateAddressDTO updateDTO) {
       Address address = service.updateAddress(updateDTO.addressID, updateDTO.country, updateDTO.city,
               updateDTO.streetName, updateDTO.buildingNumber, updateDTO.postalCode);
       address = addressDAO.updateProduct(address);
       return new AddressCreatedDTO(address.getAddressID().getId());
    }
}