package sanity.nil.order.application.product.exceptions;

import java.util.List;
import java.util.UUID;

public class ProductNotFound extends RuntimeException{

    public static ProductNotFound throwEx(UUID id) {
        String message = String.format("Product with id = %s wasn't found.", id);
        return new ProductNotFound(message);
    }

    public static ProductNotFound throwEx(String name) {
        String message = String.format("Product with name = %s wasn't found.", name);
        return new ProductNotFound(message);
    }

    public static ProductNotFound throwEx(List<UUID> ids) {
        String message = String.format("Products with ids = %s weren't found.", ids);
        return new ProductNotFound(message);
    }

    public ProductNotFound(String message) {
        super(message);
    }

}
