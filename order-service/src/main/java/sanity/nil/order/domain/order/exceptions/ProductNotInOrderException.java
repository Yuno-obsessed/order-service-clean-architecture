package sanity.nil.order.domain.order.exceptions;

import java.util.UUID;

public class ProductNotInOrderException extends RuntimeException{

    public ProductNotInOrderException(UUID id) {
        super("Product with id " + id + " is not present in order.");
    }
}
