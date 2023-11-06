package sanity.nil.order.application.common.domain.aggregate;

import sanity.nil.order.application.common.domain.event.Event;

import java.util.ArrayList;
import java.util.List;

public class BaseAggregate {

    private List<Event> events = new ArrayList<>();

    public void recordEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> pullEvents() {
        List<Event> pulled = events;
        events.clear();
        return pulled;
    }
}
