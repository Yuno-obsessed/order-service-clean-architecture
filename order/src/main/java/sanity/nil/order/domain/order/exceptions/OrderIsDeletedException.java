package sanity.nil.order.domain.order.exceptions;

import java.time.LocalDateTime;

public class OrderIsDeletedException extends RuntimeException {

    public static OrderIsDeletedException throwEx(LocalDateTime deletedAt) {
        String message = "Order was deleted at " + deletedAt.toString();
        return new OrderIsDeletedException(message);
    }

    public OrderIsDeletedException(String message) {
        super(message);
    }
}
