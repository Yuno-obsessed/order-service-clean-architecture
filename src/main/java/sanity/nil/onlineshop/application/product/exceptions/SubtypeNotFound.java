package sanity.nil.onlineshop.application.product.exceptions;

import java.util.UUID;

public class SubtypeNotFound extends RuntimeException {

    public static SubtypeNotFound throwEx(Integer id) {
        String message = String.format("Product subtype with id = %s wasn't found.", id);
        return new SubtypeNotFound(message);
    }

    public SubtypeNotFound(String message) {
        super(message);
    }
}