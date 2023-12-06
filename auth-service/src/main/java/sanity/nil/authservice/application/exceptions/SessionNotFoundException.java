package sanity.nil.authservice.application.exceptions;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException() {
        super("Such Session wasn't found");
    }
}
