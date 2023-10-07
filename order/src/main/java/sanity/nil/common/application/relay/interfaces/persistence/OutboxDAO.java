package sanity.nil.common.application.relay.interfaces.persistence;

import sanity.nil.common.application.consts.EventStatus;
import sanity.nil.common.application.relay.dto.OutboxMessage;
import sanity.nil.common.domain.event.Event;

import java.util.List;
import java.util.UUID;

public interface OutboxDAO {

   List<OutboxMessage> getAllNonProcessedMessages();

   void updateMessage(List<UUID> ids);

   void updateStatusByAggregateID(UUID aggregateID, EventStatus status);

   void addEvents(List<Event> events);
}
