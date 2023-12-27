package sanity.nil.order.infrastructure.database.orm;


import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.OrderModel;

import java.util.Optional;
import java.util.UUID;

public interface OrderORM extends JpaRepository<OrderModel, UUID> {

    Optional<OrderModel> findByIdAndUserID(UUID id, UUID userID);
}
