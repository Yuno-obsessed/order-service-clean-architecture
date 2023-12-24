package sanity.nil.userservice.application.interfaces.persistence;

import sanity.nil.userservice.application.consts.RoleType;
import sanity.nil.userservice.domain.user.vo.Role;

public interface RoleReader {

    Role getRoleByType(RoleType roleType);
}
