package sanity.nil.library.exceptions;

public class AuthBearerException extends RuntimeException{

    public AuthBearerException() {
        super("Auth Bearer wasn't found in Authorization header.");
    }
}
