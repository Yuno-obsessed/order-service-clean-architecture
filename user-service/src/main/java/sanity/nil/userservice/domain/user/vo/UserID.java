package sanity.nil.userservice.domain.user.vo;

import java.util.Objects;
import java.util.UUID;

public class UserID {

    private UUID id;

    public UserID() {
        id = UUID.randomUUID();
    }

    public UserID(UUID id) {
        this.id = id;
    }

    public UUID getID() {
        return id;
    }

    public void setID(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID orderID = (UserID) o;
        return Objects.equals(id, orderID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
