package sanity.nil.roleservice.application.exceptions;

public class ServiceNotFoundException extends RuntimeException{

    public ServiceNotFoundException() {
        super("Service not found");
    }
}
