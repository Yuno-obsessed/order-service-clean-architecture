package sanity.nil.order.application.common.domain.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEvent implements Serializable {

    private UUID eventId;

    private String eventType;

    private LocalDateTime eventTimestamp;

    public BaseEvent(String eventType) {
        eventId = UUID.randomUUID();
        this.eventType = eventType;
        eventTimestamp = LocalDateTime.now();
    }

    public String getEventType() {
        return eventType;
    }
}
