package sanity.nil.order.domain.order.exceptions;

import java.util.UUID;

public class OrderProductAlreadyIsContainedException extends RuntimeException{

    public static OrderProductAlreadyIsContainedException throwEx(UUID id) {
        String message = "Product with id = " + id + " is already contained in order.";
        return new OrderProductAlreadyIsContainedException(message);
    }

    public OrderProductAlreadyIsContainedException(String message) {
        super(message);
    }
}
