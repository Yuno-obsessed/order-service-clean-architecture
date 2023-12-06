package sanity.nil.userservice.domain.common.event;

import java.util.UUID;

public interface Event {

    UUID uniqueAggregateID();

    BaseEvent getBaseEvent();

}
