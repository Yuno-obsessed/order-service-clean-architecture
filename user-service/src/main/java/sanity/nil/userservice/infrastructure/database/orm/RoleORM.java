package sanity.nil.userservice.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.userservice.application.consts.RoleType;
import sanity.nil.userservice.infrastructure.database.models.RoleModel;

import java.util.Optional;

public interface RoleORM extends JpaRepository<RoleModel, String> {

    Optional<RoleModel> findByRoleType(RoleType roleType);
}
