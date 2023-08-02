package sanity.nil.onlineshop.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.onlineshop.infrastructure.model.ProductModel;

import java.util.UUID;

public interface ProductDAO extends JpaRepository<ProductModel, UUID> {
}
