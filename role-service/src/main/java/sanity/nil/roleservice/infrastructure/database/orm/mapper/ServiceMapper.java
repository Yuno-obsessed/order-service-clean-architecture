package sanity.nil.roleservice.infrastructure.database.orm.mapper;

import sanity.nil.roleservice.application.dto.query.ServiceQueryDTO;
import sanity.nil.roleservice.infrastructure.database.models.ServiceModel;

public class ServiceMapper {

    public static ServiceQueryDTO convertModelToQuery(ServiceModel model) {
        return new ServiceQueryDTO(model.getServiceName(), model.getPort());
    }
}
