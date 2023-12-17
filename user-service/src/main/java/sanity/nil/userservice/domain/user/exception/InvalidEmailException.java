package sanity.nil.userservice.domain.user.exception;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String message) {
        super(message);
    }

    public static InvalidEmailException invalidLength() {
        String message = "Invalid length less than 5 for firstName";
        return new InvalidEmailException(message);
    }

    public static InvalidEmailException invalidEmailFormat() {
        return new InvalidEmailException("Email specified is not a valid email.");
    }
}
