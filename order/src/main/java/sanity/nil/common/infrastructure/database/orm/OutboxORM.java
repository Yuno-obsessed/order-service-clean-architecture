package sanity.nil.common.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.common.infrastructure.database.models.OutboxModel;

import java.util.List;
import java.util.UUID;

public interface OutboxORM extends JpaRepository<OutboxModel, UUID> {

    List<OutboxModel> getAllByEventStatus(int status);

    List<OutboxModel> findAllByIdIn(List<UUID> id);

    List<OutboxModel> findAllByAggregateID(UUID aggregateID);
}
