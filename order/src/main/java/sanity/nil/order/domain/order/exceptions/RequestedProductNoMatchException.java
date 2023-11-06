package sanity.nil.order.domain.order.exceptions;

import java.util.UUID;

public class RequestedProductNoMatchException extends RuntimeException{

    public static RequestedProductNoMatchException throwEx(UUID id) {
        String message = "Requested product with id = " + id + " doesn't match any product.";
        return new RequestedProductNoMatchException(message);
    }

    public RequestedProductNoMatchException(String message) {
        super(message);
    }
}
