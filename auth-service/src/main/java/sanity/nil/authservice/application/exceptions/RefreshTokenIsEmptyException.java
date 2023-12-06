package sanity.nil.authservice.application.exceptions;

public class RefreshTokenIsEmptyException extends RuntimeException {

    public RefreshTokenIsEmptyException() {
        super("REFRESH_TOKEN cookie is empty.");
    }
}
