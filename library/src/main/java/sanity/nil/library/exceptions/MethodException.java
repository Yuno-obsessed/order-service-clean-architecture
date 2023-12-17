package sanity.nil.library.exceptions;

public class MethodException extends RuntimeException{

    public MethodException(String url, Throwable cause) {
        super("Method for url " + url + " wasn't recognized.", cause);
    }
}
