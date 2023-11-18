package sanity.nil.order.application.common.relay.interfaces.persistence;

import sanity.nil.order.application.common.consts.EventStatus;
import sanity.nil.order.application.common.relay.dto.OutboxMessage;
import sanity.nil.order.domain.common.event.Event;

import java.util.List;
import java.util.UUID;

public interface OutboxDAO {

   List<OutboxMessage> getAllNonProcessedMessages();

   void updateMessage(List<UUID> ids);

   void updateStatusByAggregateID(UUID aggregateID, EventStatus status);

   void addEvents(List<Event> events);
}
