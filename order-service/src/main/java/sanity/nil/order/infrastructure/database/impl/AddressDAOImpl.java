package sanity.nil.order.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.query.AddressQueryDTO;
import sanity.nil.order.application.order.exceptions.AddressNotFoundException;
import sanity.nil.order.application.order.interfaces.persistence.AddressDAO;
import sanity.nil.order.application.order.interfaces.persistence.AddressReader;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.infrastructure.database.models.AddressModel;
import sanity.nil.order.infrastructure.database.orm.AddressORM;
import sanity.nil.order.infrastructure.database.orm.mapper.AddressMapper;

import java.util.UUID;

@RequiredArgsConstructor
public class AddressDAOImpl implements AddressDAO, AddressReader {

    private final AddressORM addressORM;

    @Override
    public Address createAddress(Address address) {
        AddressModel newModel = addressORM.save(AddressMapper.convertEntityToModel(address));
        return AddressMapper.convertModelToEntity(newModel);
    }

    @Override
    public Address updateAddress(Address entity) {
        UUID id = entity.getAddressID().getId();
        AddressModel maybeModel = addressORM.findById(id).orElseThrow(
                () -> AddressNotFoundException.throwEx(id));
        AddressModel updatedModel = addressORM.save(AddressMapper.convertEntityToModel(entity));
        return AddressMapper.convertModelToEntity(updatedModel);
    }

    @Override
    public AddressQueryDTO getAddressQueryByID(UUID id) {
        AddressModel maybeModel = addressORM.findById(id).orElseThrow(
                () -> AddressNotFoundException.throwEx(id));
        return AddressMapper.convertModelToAddressQueryDTO(maybeModel);
    }
}
