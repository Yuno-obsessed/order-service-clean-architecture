package sanity.nil.order.domain.common.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEvent {

    private UUID eventId;

    private String eventType;

    private LocalDateTime eventTimestamp;

    public BaseEvent(String eventType) {
        eventId = UUID.randomUUID();
        this.eventType = eventType;
        eventTimestamp = LocalDateTime.now();
    }

}
