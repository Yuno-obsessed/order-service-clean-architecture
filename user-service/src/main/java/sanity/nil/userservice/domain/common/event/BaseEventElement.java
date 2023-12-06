package sanity.nil.userservice.domain.common.event;

import java.io.Serializable;

public class BaseEventElement implements Serializable {

    private BaseEvent baseEvent;

    public BaseEventElement() {
    }

    public BaseEventElement(BaseEvent baseEvent) {
        this.baseEvent = baseEvent;
    }

    public BaseEvent getBaseEvent() {
        return baseEvent;
    }

    public void setBaseEvent(BaseEvent baseEvent) {
        this.baseEvent = baseEvent;
    }
}
