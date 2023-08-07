package sanity.nil.onlineshop.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.onlineshop.infrastructure.database.model.ProductSubtypeModel;

public interface ProductSubtypeORM extends JpaRepository<ProductSubtypeModel, Integer> {
}
