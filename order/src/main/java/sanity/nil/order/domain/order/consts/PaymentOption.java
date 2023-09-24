package sanity.nil.order.domain.order.consts;

public enum PaymentOption {
    ONE_TRANSFER(0, "One transfer"),
    TWO_TRANSFERS(1, "Two transfers"),
    IN_CREDIT(2, "In credit");

    private int code;
    private String value;

    private PaymentOption(int code, String value) {
       this.code = code;
       this.value = value;
    }

    public static PaymentOption fromString(String value) {
        for (PaymentOption paymentOption : values()) {
            if (paymentOption.value.equals(value)) {
                return paymentOption;
            }
        }
        return null;
    }
}
