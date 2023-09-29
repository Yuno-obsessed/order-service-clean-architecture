package sanity.nil.order.domain.product.exceptions;

public class DiscountNotFoundException extends RuntimeException{

    public static DiscountNotFoundException throwEx(Integer discount, String obj) {
        String message = "No discount with " + obj + " = " + discount + " found.";
        return new DiscountNotFoundException(message);
    }

    public DiscountNotFoundException(String message) {
        super(message);
    }
}
