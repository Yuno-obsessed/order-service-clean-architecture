package sanity.nil.userservice.domain.user.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }

    public static InvalidPasswordException invalidLength() {
        String message = "Invalid length less than 2 for firstName";
        return new InvalidPasswordException(message);
    }

    public static InvalidPasswordException noSpecialChars() {
        String message = "No special required char as '!','.','_' in password.";
        return new InvalidPasswordException(message);
    }

    public static InvalidPasswordException noDigit() {
        return new InvalidPasswordException("No digit in password.");
    }

    public static InvalidPasswordException noUpperCase() {
        return new InvalidPasswordException("No upper case character in password.");
    }
}
