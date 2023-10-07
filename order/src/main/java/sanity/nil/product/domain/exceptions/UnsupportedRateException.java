package sanity.nil.product.domain.exceptions;

import java.math.BigDecimal;

public class UnsupportedRateException extends RuntimeException{

    public static UnsupportedRateException throwEx(BigDecimal rate) {
        String message = "Unsupported rate set, can't be equal to " + rate;
        return new UnsupportedRateException(message);
    }

    public UnsupportedRateException(String message) {
        super(message);
    }
}
