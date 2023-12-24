package sanity.nil.userservice.application.exceptions;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException() {
        super("Such role wasn't found.");
    }
}
