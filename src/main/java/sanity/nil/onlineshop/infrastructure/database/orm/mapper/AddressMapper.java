package sanity.nil.onlineshop.infrastructure.database.orm.mapper;

import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;
import sanity.nil.onlineshop.domain.order.entity.Address;
import sanity.nil.onlineshop.domain.order.vo.AddressID;
import sanity.nil.onlineshop.infrastructure.database.model.AddressModel;

public class AddressMapper {

    public static Address convertModelToEntity(AddressModel model) {
        return new Address(new AddressID(model.getAddressId()),
                model.getCountry(), model.getCity(), model.getStreetName(), model.getBuildingNumber(), model.getPostalCode());
    }

    public static AddressModel convertEntityToModel(Address address) {
        return new AddressModel(address.getAddressID().getId(), address.getCountry(), address.getCity(),
                address.getStreetName(), address.getBuildingNumber(), address.getPostalCode());
    }

    public static AddressDTO convertModelToAddressDTO(AddressModel model) {
        return new AddressDTO(model.getAddressId(), model.getCountry(), model.getCity(),
                model.getStreetName(), model.getBuildingNumber(), model.getPostalCode());
    }
}
