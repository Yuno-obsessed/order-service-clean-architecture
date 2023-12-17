package sanity.nil.order.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.ProductWishListModel;

import java.util.UUID;

public interface ProductWishListORM extends JpaRepository<ProductWishListModel, UUID> {
}
