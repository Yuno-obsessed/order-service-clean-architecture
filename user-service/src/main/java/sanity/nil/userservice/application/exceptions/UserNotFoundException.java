package sanity.nil.userservice.application.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Such user wasn't found.");
    }
}
