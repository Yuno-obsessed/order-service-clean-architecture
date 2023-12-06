package sanity.nil.order.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.ProductImageModel;

import java.util.List;
import java.util.UUID;

public interface ProductImageORM extends JpaRepository<ProductImageModel, UUID> {

    List<ProductImageModel> getAllByProductId(UUID productID);
}
