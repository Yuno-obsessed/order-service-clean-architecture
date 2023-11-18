package sanity.nil.order.application.order.exceptions;

public class ImagesLimitException extends RuntimeException {

    public ImagesLimitException() {
        super("Can't upload more than 8 images for a product.");
    }

}
