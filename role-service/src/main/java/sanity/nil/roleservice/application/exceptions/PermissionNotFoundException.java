package sanity.nil.roleservice.application.exceptions;

public class PermissionNotFoundException extends RuntimeException{

    public PermissionNotFoundException() {
        super("Permission not found");
    }
}
