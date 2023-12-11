package sanity.nil.roleservice.infrastructure.database.orm.mapper;

import sanity.nil.roleservice.application.dto.query.PermissionQueryDTO;
import sanity.nil.roleservice.infrastructure.database.models.PermissionModel;

public class PermissionMapper {

    public static PermissionQueryDTO convertModelToQuery(PermissionModel model) {
        return new PermissionQueryDTO(model.getServiceName(), model.getUri(), model.getMethod(), model.getRoles());
    }
}
