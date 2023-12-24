package sanity.nil.userservice.domain.user.events;

import sanity.nil.userservice.domain.common.event.BaseEvent;
import sanity.nil.userservice.domain.common.event.Event;

import java.io.Serializable;
import java.util.UUID;

public class UserCreatedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID userID;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public UserCreatedEvent() {}

    public UserCreatedEvent(UUID userID, String username, String firstName, String lastName, String email) {
        this.baseEvent = new BaseEvent("UserCreated");
        this.userID = userID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public UUID uniqueAggregateID() {
        return userID;
    }

    @Override
    public BaseEvent getBaseEvent() {
        return baseEvent;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
