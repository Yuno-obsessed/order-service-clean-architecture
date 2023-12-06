package sanity.nil.order.application.order.exceptions;

import java.util.UUID;

public class AddressNotFoundException extends RuntimeException{

    public static AddressNotFoundException throwEx(UUID id) {
        String message = String.format("Address with id = %s wasn't found.", id);
        return new AddressNotFoundException(message);
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
}
