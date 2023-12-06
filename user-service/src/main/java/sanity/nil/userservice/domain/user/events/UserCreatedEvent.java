package sanity.nil.userservice.domain.user.events;

import sanity.nil.userservice.domain.common.event.BaseEvent;
import sanity.nil.userservice.domain.common.event.Event;

import java.io.Serializable;
import java.util.UUID;

public class UserCreatedEvent implements Event, Serializable {


    @Override
    public UUID uniqueAggregateID() {
        return null;
    }

    @Override
    public BaseEvent getBaseEvent() {
        return null;
    }
}
