package sanity.nil.order.domain.order.vo;

import java.util.Objects;
import java.util.UUID;

public class OrderID {

    private UUID id;

    public OrderID() {
        id = UUID.randomUUID();
    }

    public OrderID(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderID orderID = (OrderID) o;
        return Objects.equals(id, orderID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
