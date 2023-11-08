package sanity.nil.order.application.order.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{

    public static OrderNotFoundException throwEx(UUID id) {
        String message = String.format("Order with id = %s wasn't found.", id);
        return new OrderNotFoundException(message);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
