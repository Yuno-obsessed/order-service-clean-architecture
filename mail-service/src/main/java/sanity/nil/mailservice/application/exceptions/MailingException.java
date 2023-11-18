package sanity.nil.mailservice.application.exceptions;

public class MailingException extends RuntimeException {

    public MailingException(Throwable cause) {
        super("Mailing encountered an error: ", cause);
    }
}
