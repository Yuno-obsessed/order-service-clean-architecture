package sanity.nil.order.domain.order.exceptions;

public class OrderWasDeliveredException extends RuntimeException{

    public static OrderWasDeliveredException throwEx() {
        String message = "Order was already delivered, so it's terminated.";
        return new OrderWasDeliveredException(message);
    }

    public OrderWasDeliveredException(String message) {
        super(message);
    }
}
