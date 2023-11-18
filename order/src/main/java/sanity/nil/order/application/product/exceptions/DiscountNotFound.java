package sanity.nil.order.application.product.exceptions;

import java.util.UUID;

public class DiscountNotFound extends RuntimeException {

    public static DiscountNotFound throwEx(UUID id) {
        String message = "No discount with id = " + id + " was found.";
        return new DiscountNotFound(message);
    }

    public DiscountNotFound(String message) {
        super(message);
    }
}
