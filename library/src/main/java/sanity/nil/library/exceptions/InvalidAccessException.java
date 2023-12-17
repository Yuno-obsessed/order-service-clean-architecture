package sanity.nil.library.exceptions;

public class InvalidAccessException extends RuntimeException {

    public InvalidAccessException() {
        super("Auth token is invalid");
    }
}
