package sanity.nil.order.domain.order.consts;

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

    public static OrderStatus fromString(String value) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.value.equals(value)) {
                return orderStatus;
            }
        }
        return null;
    }
}
