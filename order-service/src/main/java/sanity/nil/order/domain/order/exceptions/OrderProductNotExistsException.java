package sanity.nil.order.domain.order.exceptions;

import java.util.UUID;

public class OrderProductNotExistsException extends RuntimeException{

    public static OrderProductNotExistsException throwEx(UUID id) {
        String message = "Product with id = " + id + " doesn't exist in order.";
        return new OrderProductNotExistsException(message);
    }

    public OrderProductNotExistsException(String message) {
        super(message);
    }
}
