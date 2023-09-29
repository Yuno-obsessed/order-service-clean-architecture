package sanity.nil.order.domain.order.exceptions;

public class OrderProductsEmptyException extends RuntimeException {

    public OrderProductsEmptyException() {
        super("There are no products to create order with.");
    }
}
