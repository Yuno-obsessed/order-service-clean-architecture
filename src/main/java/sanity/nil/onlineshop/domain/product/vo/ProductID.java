package sanity.nil.onlineshop.domain.product.vo;

import java.util.UUID;

public class ProductID {

    private UUID id;

    public ProductID() {
        this.id = UUID.randomUUID();
    }

    public ProductID(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
