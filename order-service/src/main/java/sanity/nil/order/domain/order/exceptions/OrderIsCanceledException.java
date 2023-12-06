package sanity.nil.order.domain.order.exceptions;

public class OrderIsCanceledException extends RuntimeException{

    public static OrderIsCanceledException throwEx() {
        String message = "Order is canceled.";
        return new OrderIsCanceledException(message);
    }

    public OrderIsCanceledException(String message) {
        super(message);
    }
}
