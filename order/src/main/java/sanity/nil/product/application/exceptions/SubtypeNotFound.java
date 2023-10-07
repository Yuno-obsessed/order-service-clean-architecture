package sanity.nil.product.application.exceptions;

public class SubtypeNotFound extends RuntimeException {

    public static SubtypeNotFound throwEx(Integer id) {
        String message = String.format("Product subtype with id = %s wasn't found.", id);
        return new SubtypeNotFound(message);
    }

    public SubtypeNotFound(String message) {
        super(message);
    }
}