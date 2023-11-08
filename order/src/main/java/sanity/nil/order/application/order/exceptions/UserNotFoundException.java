package sanity.nil.order.application.order.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{

    public static UserNotFoundException throwEx(UUID id) {
        String message = String.format("User with id = %s wasn't found.", id);
        return new UserNotFoundException(message);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
