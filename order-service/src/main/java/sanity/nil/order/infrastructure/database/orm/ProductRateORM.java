package sanity.nil.order.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.ProductRateModel;

import java.util.UUID;

public interface ProductRateORM extends JpaRepository<ProductRateModel, UUID> {
}
