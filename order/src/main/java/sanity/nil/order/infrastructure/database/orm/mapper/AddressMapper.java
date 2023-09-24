package sanity.nil.order.infrastructure.database.orm.mapper;

import sanity.nil.order.application.order.dto.address.AddressDTO;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.vo.AddressID;
import sanity.nil.order.infrastructure.database.models.AddressModel;

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

    public static AddressDTO convertModelToAddressDTO(AddressModel model) {
        return new AddressDTO(model.getId(), model.getCountry(), model.getCity(),
                model.getStreetName(), model.getBuildingNumber(), model.getPostalCode());
    }
}
