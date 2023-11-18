package sanity.nil.order.application.common.exceptions;

public class StorageException extends RuntimeException {

    public StorageException(Throwable cause) {
        super(String.format("Storage encountered an exception: %s", cause));
    }
}
