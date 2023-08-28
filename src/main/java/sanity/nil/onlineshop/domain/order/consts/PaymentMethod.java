package sanity.nil.onlineshop.domain.order.consts;

public enum PaymentMethod {
    CREDIT_CARD(1, "Credit card"),
    CRYPTO_CURRENCY(2, "Crypto currency"),
    CASH(3, "Cash");

    private int code;
    private String value;

    private PaymentMethod(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
