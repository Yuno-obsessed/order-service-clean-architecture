package sanity.nil.order.application.order.exceptions;

import java.util.UUID;

public class AddressNotFound extends RuntimeException{

    public static AddressNotFound throwEx(UUID id) {
        String message = String.format("Address with id = %s wasn't found.", id);
        return new AddressNotFound(message);
    }

    public AddressNotFound(String message) {
        super(message);
    }
}
