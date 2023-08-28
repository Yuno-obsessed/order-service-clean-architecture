package sanity.nil.onlineshop.application.order.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;
import sanity.nil.onlineshop.application.order.dto.address.UpdateAddressDTO;
import sanity.nil.onlineshop.application.order.interfaces.interactors.UpdateAddressInteractor;
import sanity.nil.onlineshop.application.order.interfaces.query.AddressDAO;
import sanity.nil.onlineshop.application.order.interfaces.query.AddressReader;
import sanity.nil.onlineshop.domain.order.entity.Address;
import sanity.nil.onlineshop.domain.order.service.AddressService;

@RequiredArgsConstructor
public class UpdateAddressInteractorImpl implements UpdateAddressInteractor {

    private final AddressDAO addressDAO;
    private final AddressReader addressReader;
    private final AddressService service;

    @Override
    public AddressDTO update(UpdateAddressDTO updateDTO) {
       Address address = service.updateAddress(updateDTO.addressID, updateDTO.country, updateDTO.city,
               updateDTO.streetName, updateDTO.buildingNumber, updateDTO.postalCode);
       address = addressDAO.updateProduct(address);
       return addressReader.getAddressDTOById(address.getAddressID().getId());
    }
}
