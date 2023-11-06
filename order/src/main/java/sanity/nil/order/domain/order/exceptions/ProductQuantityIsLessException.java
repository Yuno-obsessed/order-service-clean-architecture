package sanity.nil.order.domain.order.exceptions;

import java.util.UUID;

public class ProductQuantityIsLessException extends RuntimeException{

    public static ProductQuantityIsLessException throwEx(UUID id) {
        String message = "Requested product with id = " + id + " has quantity greater than in storage.";
        return new ProductQuantityIsLessException(message);
    }

    public ProductQuantityIsLessException(String message) {
        super(message);
    }
}
