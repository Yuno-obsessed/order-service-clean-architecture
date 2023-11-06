package sanity.nil.order.infrastructure.database.orm.mapper;

import sanity.nil.order.application.order.dto.boundary.AddressDTO;
import sanity.nil.order.application.order.dto.query.AddressQueryDTO;
import sanity.nil.order.infrastructure.database.models.AddressModel;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.vo.AddressID;

public class AddressMapper {

    public static Address convertModelToEntity(AddressModel model) {
        return new Address(new AddressID(model.getId()),
                model.getCountry(), model.getCity(), model.getStreetName(), model.getBuildingNumber(), model.getPostalCode());
    }

    public static AddressModel convertEntityToModel(Address address) {
        AddressModel model = new AddressModel(address.getCountry(), address.getCity(),
                address.getStreetName(), address.getBuildingNumber(), address.getPostalCode());
        model.setId(address.getAddressID().getId());
        return model;
    }

    public static AddressQueryDTO convertModelToAddressQueryDTO(AddressModel model) {
        return new AddressQueryDTO(model.getId(), model.getCountry(), model.getCity(),
                model.getStreetName(), model.getBuildingNumber(), model.getPostalCode());
    }

    public static AddressDTO convertEntityToAddressDTO(Address entity) {
        return new AddressDTO(entity.getAddressID().getId(), entity.getFullAddress());
    }
}
