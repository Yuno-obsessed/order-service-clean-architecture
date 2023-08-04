package sanity.nil.onlineshop.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.onlineshop.infrastructure.database.model.ProductModel;

import java.util.UUID;

public interface ProductORM extends JpaRepository<ProductModel, UUID> {
}
