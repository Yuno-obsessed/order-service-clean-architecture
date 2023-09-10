package sanity.nil.order.application.product.exceptions;

import java.util.UUID;

public class ProductIsDeleted extends RuntimeException{

    public static ProductIsDeleted throwEx(UUID id) {
        String message = String.format("Product with id = %s is deleted.", id);
        return new ProductIsDeleted(message);
    }

    public ProductIsDeleted(String message) {
        super(message);
    }

}
