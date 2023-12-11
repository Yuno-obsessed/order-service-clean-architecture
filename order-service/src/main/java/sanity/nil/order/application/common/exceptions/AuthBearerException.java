package sanity.nil.order.application.common.exceptions;

public class AuthBearerException extends RuntimeException{

    public AuthBearerException() {
        super("Auth Bearer wasn't found in Authorization header.");
    }
}
