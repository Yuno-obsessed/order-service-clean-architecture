package sanity.nil.order.domain.product.exceptions;

import java.math.BigDecimal;

public class UnsupportedPriceException extends RuntimeException{

    public static UnsupportedPriceException throwEx(BigDecimal price) {
        String message = "Unsupported price set, can't be equal to " + price;
        return new UnsupportedPriceException(message);
    }

    public UnsupportedPriceException(String message) {
        super(message);
    }
}
