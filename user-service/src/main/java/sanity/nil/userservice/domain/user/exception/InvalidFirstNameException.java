package sanity.nil.userservice.domain.user.exception;

public class InvalidFirstNameException extends RuntimeException{

    public InvalidFirstNameException(String message) {
        super(message);
    }

    public static InvalidFirstNameException invalidLength() {
        String message = "Invalid length less than 2 for firstName";
        return new InvalidFirstNameException(message);
    }

    public static InvalidFirstNameException invalidCharactersOccurred(String invalidChar) {
        String message = "One of such invalid chars occurred '" + invalidChar + "' occurred.";
        return new InvalidFirstNameException(message);
    }
}
