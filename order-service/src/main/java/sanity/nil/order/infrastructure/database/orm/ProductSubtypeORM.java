package sanity.nil.order.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.ProductSubtypeModel;

public interface ProductSubtypeORM extends JpaRepository<ProductSubtypeModel, Integer> {
}
