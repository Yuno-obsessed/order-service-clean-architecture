package sanity.nil.order.application.common.application.relay.interfaces.persistence;

import sanity.nil.order.application.common.application.consts.EventStatus;
import sanity.nil.order.application.common.domain.event.Event;
import sanity.nil.order.application.common.application.relay.dto.OutboxMessage;

import java.util.List;
import java.util.UUID;

public interface OutboxDAO {

   List<OutboxMessage> getAllNonProcessedMessages();

   void updateMessage(List<UUID> ids);

   void updateStatusByAggregateID(UUID aggregateID, EventStatus status);

   void addEvents(List<Event> events);
}
