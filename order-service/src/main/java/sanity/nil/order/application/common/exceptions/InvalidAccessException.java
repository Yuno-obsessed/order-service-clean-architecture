package sanity.nil.order.application.common.exceptions;

public class InvalidAccessException extends RuntimeException {

    public InvalidAccessException() {
        super("Auth token is invalid");
    }
}
