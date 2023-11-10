package sanity.nil.order.application.order.exceptions;

import java.util.UUID;

public class ProductQuantityMismatch extends RuntimeException {

    public static ProductQuantityMismatch throwEx(UUID id, Integer actualQuantity, Integer gotQuantity) {
        String message = String.format("Product with id = %s has quantity = %d, wanted to get = %d.", id, actualQuantity, gotQuantity);
        return new ProductQuantityMismatch(message);
    }

    public ProductQuantityMismatch(String message) {
        super(message);
    }
}
