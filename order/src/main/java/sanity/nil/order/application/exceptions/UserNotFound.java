package sanity.nil.order.application.exceptions;

import java.util.UUID;

public class UserNotFound extends RuntimeException{

    public static UserNotFound throwEx(UUID id) {
        String message = String.format("User with id = %s wasn't found.", id);
        return new UserNotFound(message);
    }

    public UserNotFound(String message) {
        super(message);
    }
}
