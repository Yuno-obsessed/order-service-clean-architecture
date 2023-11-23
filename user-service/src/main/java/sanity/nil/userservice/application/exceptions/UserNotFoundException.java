package sanity.nil.userservice.application.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(String.format("User with username = %s wasn't found.", message));
    }
}
