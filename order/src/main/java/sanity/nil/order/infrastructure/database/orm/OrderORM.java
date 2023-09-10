package sanity.nil.order.infrastructure.database.orm;


import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.model.OrderModel;

import java.util.UUID;

public interface OrderORM extends JpaRepository<OrderModel, UUID> {
}
