package sanity.nil.order.domain.order.exceptions;

import java.util.UUID;

public class OrderProductNotExists extends RuntimeException{

    public static OrderProductNotExists throwEx(UUID id) {
        String message = "Product with id = " + id + " doesn't exist in order.";
        return new OrderProductNotExists(message);
    }

    public OrderProductNotExists(String message) {
        super(message);
    }
}
