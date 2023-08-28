package sanity.nil.onlineshop.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.onlineshop.infrastructure.database.model.OrderModel;

import java.util.UUID;

public interface OrderORM extends JpaRepository<OrderModel, UUID> {
}
