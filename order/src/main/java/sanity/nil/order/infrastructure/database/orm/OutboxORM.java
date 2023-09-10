package sanity.nil.order.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sanity.nil.order.infrastructure.database.model.OutboxModel;

import java.util.List;
import java.util.UUID;

public interface OutboxORM extends JpaRepository<OutboxModel, UUID> {

    List<OutboxModel> getAllByEventStatus(int status);

    List<OutboxModel> findAllByIdIn(List<UUID> id);

    List<OutboxModel> findAllByAggregateID(UUID aggregateID);
}
