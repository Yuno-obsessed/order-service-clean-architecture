package sanity.nil.product.domain.exceptions;

public class UnsupportedQuantityException extends RuntimeException{

   public static UnsupportedQuantityException throwEx(Integer quantity) {
       String message = "Unsupported quantity set, can't be equal to " + quantity;
       return new UnsupportedQuantityException(message);
   }

   public UnsupportedQuantityException(String message) {
       super(message);
   }
}
