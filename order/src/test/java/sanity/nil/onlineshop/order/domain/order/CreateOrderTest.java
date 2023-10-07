package sanity.nil.onlineshop.order.domain.order;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sanity.nil.order.application.interfaces.persistence.OrderDAO;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;
import sanity.nil.onlineshop.order.infrastructure.OrderInMemoryDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sanity.nil.onlineshop.product.util.EntityGenerator.generateActiveDiscount;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateOrderTest {

    private static OrderDAO orderDAO;
    private static OrderService orderService;

    @BeforeAll
    public static void initTest() {
        orderDAO = new OrderInMemoryDAO();
        orderService = new OrderService();
    }

    @Test
    public void successCreateOrder() {
        List<OrderProduct> products = new ArrayList<>();
        products.add(new OrderProduct(UUID.randomUUID(), "product1",
                BigDecimal.valueOf(30.0), generateActiveDiscount(30), 4));
        Order order = orderService.create(UUID.randomUUID(), UUID.randomUUID(),
                products, PaymentMethod.CASH.name(), PaymentOption.ONE_TRANSFER.name());
        Order createdOrder = orderDAO.create(order);
        assertThat(order).isEqualTo(createdOrder);
    }
}
