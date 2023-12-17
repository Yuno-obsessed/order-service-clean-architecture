package sanity.nil.library.exceptions;

public class AccessExpiredException extends RuntimeException {

    public AccessExpiredException() {
        super("Access expired. Use refresh-token to generate a new access token.");
    }
}
