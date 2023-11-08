package sanity.nil.order.application.common.domain.event;

import java.util.UUID;

public interface Event {

    byte[] bytes();

    UUID uniqueAggregateID();

    BaseEvent getBaseEvent();

}
