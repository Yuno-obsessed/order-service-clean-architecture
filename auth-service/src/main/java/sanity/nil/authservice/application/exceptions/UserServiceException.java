package sanity.nil.authservice.application.exceptions;

public class UserServiceException extends RuntimeException {

    public UserServiceException(Throwable cause) {
        super("Error in user-service.", cause);
    }
}
