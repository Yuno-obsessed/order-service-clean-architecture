package sanity.nil.onlineshop.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.onlineshop.infrastructure.database.model.ProductTypeModel;

public interface ProductTypeORM extends JpaRepository<ProductTypeModel, Integer> {
}
