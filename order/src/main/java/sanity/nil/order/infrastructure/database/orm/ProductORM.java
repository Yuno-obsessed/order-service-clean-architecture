package sanity.nil.order.infrastructure.database.orm;


import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.ProductModel;

import java.util.List;
import java.util.UUID;

public interface ProductORM extends JpaRepository<ProductModel, UUID> {

    List<ProductModel> getAllByIdIn(List<UUID> ids);
}
