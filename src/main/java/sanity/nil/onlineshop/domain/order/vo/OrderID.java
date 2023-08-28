package sanity.nil.onlineshop.domain.order.vo;

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
}
