package sanity.nil.order.infrastructure.database.orm;


import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.AddressModel;

import java.util.UUID;

public interface AddressORM extends JpaRepository<AddressModel, UUID> {
}
