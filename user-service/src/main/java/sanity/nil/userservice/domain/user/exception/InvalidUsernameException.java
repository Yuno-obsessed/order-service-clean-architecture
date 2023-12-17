package sanity.nil.userservice.domain.user.exception;

public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException(String message) {
        super(message);
    }

    public static InvalidUsernameException invalidLength() {
        String message = "Invalid length less than 5 for firstName";
        return new InvalidUsernameException(message);
    }

    public static InvalidUsernameException invalidCharactersOccurred(String invalidChar) {
        String message = "One of such invalid chars occurred '" + invalidChar + "' occurred.";
        return new InvalidUsernameException(message);
    }
}
