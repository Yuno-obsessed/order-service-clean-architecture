package sanity.nil.order.domain.order.exceptions;

import java.util.UUID;

public class OrderProductAlreadyIsContained extends RuntimeException{

    public static OrderProductAlreadyIsContained throwEx(UUID id) {
        String message = "Product with id = " + id + " is already contained in order.";
        return new OrderProductAlreadyIsContained(message);
    }

    public OrderProductAlreadyIsContained(String message) {
        super(message);
    }
}
