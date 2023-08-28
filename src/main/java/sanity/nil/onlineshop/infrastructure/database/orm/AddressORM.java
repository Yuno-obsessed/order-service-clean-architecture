package sanity.nil.onlineshop.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.onlineshop.infrastructure.database.model.AddressModel;

import java.util.UUID;

public interface AddressORM extends JpaRepository<AddressModel, UUID> {
}
