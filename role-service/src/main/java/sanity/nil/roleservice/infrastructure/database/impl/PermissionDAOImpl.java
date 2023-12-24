package sanity.nil.roleservice.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.roleservice.application.dto.query.PermissionQueryDTO;
import sanity.nil.roleservice.application.exceptions.PermissionNotFoundException;
import sanity.nil.roleservice.application.interfaces.persistence.PermissionReader;
import sanity.nil.roleservice.infrastructure.database.models.PermissionModel;
import sanity.nil.roleservice.infrastructure.database.orm.PermissionORM;
import sanity.nil.roleservice.infrastructure.database.orm.mapper.PermissionMapper;

@RequiredArgsConstructor
public class PermissionDAOImpl implements PermissionReader {

    private final PermissionORM permissionORM;

    @Override
    public PermissionQueryDTO getPermissionByURIAndVerb(String uri, String verb) {
        PermissionModel permissionModel = permissionORM.findByUriAndVerb(uri, verb).orElseThrow(
                PermissionNotFoundException::new
        );
        return PermissionMapper.convertModelToQuery(permissionModel);
    }
}
