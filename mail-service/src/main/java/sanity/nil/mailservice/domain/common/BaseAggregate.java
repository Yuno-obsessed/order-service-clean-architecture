package sanity.nil.mailservice.domain.common;

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
        List<Event> pulled = new ArrayList<>(events);
        events.clear();
        return pulled;
    }
}
