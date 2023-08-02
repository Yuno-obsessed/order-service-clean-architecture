package sanity.nil.onlineshop.application.product.exceptions;

import java.util.UUID;

public class ProductNotFound extends RuntimeException{

    public static ProductNotFound throwEx(UUID id) {
        String message = String.format("Product with id = %s wasn't found.", id);
        return new ProductNotFound(message);
    }

    public ProductNotFound(String message) {
        super(message);
    }

}
