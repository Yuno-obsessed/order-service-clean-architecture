package sanity.nil.order.order.domain.order;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;
import sanity.nil.order.util.EntityGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({MockitoExtension.class})
public class CreateOrderTest {

    private static OrderService orderService;

    private static List<UUID> productIDs;
    private static List<OrderProduct> products = new ArrayList<>();

    @BeforeAll
    public static void initTest() {
        orderService = new OrderService();
        productIDs = List.of(UUID.randomUUID(), UUID.randomUUID());
        products.add(new OrderProduct(productIDs.get(0), "product1",
                BigDecimal.valueOf(1200.0), EntityGenerator.generateActiveDiscount(30), 4));
        products.add(new OrderProduct(productIDs.get(1), "product2",
                BigDecimal.valueOf(500.0), EntityGenerator.generateActiveDiscount(30), 2));
    }

//    @Test
//    public void successCreateOrder() {
//        int validQuantityProduct1 = 3;
//        int validQuantityProduct2 = 1;
//        Order order = orderService.create(UUID.randomUUID(), UUID.randomUUID(),
//                products, PaymentMethod.CASH.name(), PaymentOption.ONE_TRANSFER.name());
//
//        assertThatNoException().isThrownBy(
//                () -> orderService.create(UUID.randomUUID(), UUID.randomUUID(),
//                        products, requestedProducts, PaymentMethod.CASH.name(), PaymentOption.ONE_TRANSFER.name())
//        );
//        assertThat(order.getProducts()).hasSize(2);
//        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CREATED);
//        assertThat(order.getEvents()).hasSize(1);
//    }
//
//    @Test
//    public void throwsOrderProductsEmpty() {
//        assertThatExceptionOfType(OrderProductsEmptyException.class).isThrownBy(
//                () -> orderService.create(UUID.randomUUID(), UUID.randomUUID(), null, null,
//                        PaymentMethod.CREDIT_CARD.name(), PaymentOption.TWO_TRANSFERS.name())
//        );
//    }
}
