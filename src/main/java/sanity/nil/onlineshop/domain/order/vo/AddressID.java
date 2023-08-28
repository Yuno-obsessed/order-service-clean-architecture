package sanity.nil.onlineshop.domain.order.vo;

import java.util.UUID;

public class AddressID {

    private UUID id;

    public AddressID() {
        this.id = UUID.randomUUID();
    }

    public AddressID(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
