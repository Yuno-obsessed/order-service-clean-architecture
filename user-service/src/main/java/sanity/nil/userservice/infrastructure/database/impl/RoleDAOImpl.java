package sanity.nil.userservice.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.userservice.application.consts.RoleType;
import sanity.nil.userservice.application.exceptions.RoleNotFoundException;
import sanity.nil.userservice.application.interfaces.persistence.RoleReader;
import sanity.nil.userservice.domain.user.vo.Role;
import sanity.nil.userservice.infrastructure.database.models.RoleModel;
import sanity.nil.userservice.infrastructure.database.orm.RoleORM;

@RequiredArgsConstructor
public class RoleDAOImpl implements RoleReader {

    private final RoleORM roleORM;

    @Override
    public Role getRoleByType(RoleType roleType) {
        RoleModel maybeRole = roleORM.findByRoleType(roleType)
                .orElseThrow(RoleNotFoundException::new);
        return new Role(maybeRole.getRoleType().name(), maybeRole.getCreatedAt());
    }
}
