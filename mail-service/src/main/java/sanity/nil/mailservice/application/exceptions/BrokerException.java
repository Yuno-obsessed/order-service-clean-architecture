package sanity.nil.mailservice.application.exceptions;

public class BrokerException extends RuntimeException {

    public BrokerException(Throwable cause) {
        super("Broker encountered an error: ", cause);
    }
}
