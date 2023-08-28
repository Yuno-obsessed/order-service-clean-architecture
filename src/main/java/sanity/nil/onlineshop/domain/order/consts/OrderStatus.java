package sanity.nil.onlineshop.domain.order.consts;

public enum OrderStatus {
    CREATED(0, "Created"),
    CANCELED(1, "Canceled"),
    PAID(2, "Paid"),
    DELIVERED(3, "Delivered");

    private OrderStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    private int code;
    private String value;
}
