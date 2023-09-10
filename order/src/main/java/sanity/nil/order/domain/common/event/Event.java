package sanity.nil.order.domain.common.event;

import java.util.UUID;

public interface Event {

    byte[] bytes();

    UUID uniqueAggregateID();
}
