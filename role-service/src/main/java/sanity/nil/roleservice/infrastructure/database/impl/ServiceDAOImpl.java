package sanity.nil.roleservice.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.roleservice.application.dto.query.ServiceQueryDTO;
import sanity.nil.roleservice.application.exceptions.ServiceNotFoundException;
import sanity.nil.roleservice.application.interfaces.persistence.ServiceReader;
import sanity.nil.roleservice.infrastructure.database.models.ServiceModel;
import sanity.nil.roleservice.infrastructure.database.orm.ServiceORM;
import sanity.nil.roleservice.infrastructure.database.orm.mapper.ServiceMapper;

@RequiredArgsConstructor
public class ServiceDAOImpl implements ServiceReader {

    private final ServiceORM serviceORM;

    @Override
    public ServiceQueryDTO getActiveByService(String service, Boolean active) {
        ServiceModel maybeService = serviceORM.findByServiceNameAndActive(service, active).orElseThrow(
                ServiceNotFoundException::new
        );
        return ServiceMapper.convertModelToQuery(maybeService);

    }
}
