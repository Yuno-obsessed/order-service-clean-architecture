package sanity.nil.product.domain.vo;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID productID = (ProductID) o;
        return Objects.equals(id, productID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
