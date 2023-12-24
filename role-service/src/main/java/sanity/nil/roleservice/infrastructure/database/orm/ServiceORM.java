package sanity.nil.roleservice.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.roleservice.infrastructure.database.models.ServiceModel;

import java.util.Optional;

public interface ServiceORM extends JpaRepository<ServiceModel, String> {

    Optional<ServiceModel> findByServiceNameAndActive(String serviceName, Boolean active);
}
