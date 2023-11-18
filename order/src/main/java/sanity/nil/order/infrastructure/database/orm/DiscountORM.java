package sanity.nil.order.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.DiscountModel;

import java.util.UUID;

public interface DiscountORM extends JpaRepository<DiscountModel, UUID> {
}
