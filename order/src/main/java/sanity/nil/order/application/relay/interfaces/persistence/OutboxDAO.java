package sanity.nil.order.application.relay.interfaces.persistence;

import sanity.nil.order.application.common.consts.EventStatus;
import sanity.nil.order.application.relay.dto.OutboxMessage;

import java.util.List;
import java.util.UUID;

public interface OutboxDAO {

   List<OutboxMessage> getAllNonProcessedMessages();

   void updateMessage(List<UUID> ids);

   void updateStatusByAggregateID(UUID aggregateID, EventStatus status);
}
