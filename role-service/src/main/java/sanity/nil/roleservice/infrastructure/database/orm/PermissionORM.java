package sanity.nil.roleservice.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.roleservice.infrastructure.database.models.PermissionModel;

import java.util.Optional;

public interface PermissionORM extends JpaRepository<PermissionModel, Integer> {

    Optional<PermissionModel> findByUriAndVerb(String uri, String verb);
}
