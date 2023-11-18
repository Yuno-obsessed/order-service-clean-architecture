package sanity.nil.mailservice.domain.common;

import java.util.UUID;

public interface Event {

    byte[] bytes();

    UUID uniqueAggregateID();

    BaseEvent getBaseEvent();

}
