package sanity.nil.order.application.order.exceptions;

public class OrdersNotFoundException extends RuntimeException {

    public static OrdersNotFoundException throwEx() {
        return new OrdersNotFoundException("No orders were found.");
    }

    public OrdersNotFoundException(String message) {
        super(message);
    }
}
