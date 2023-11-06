package sanity.nil.order.order.domain.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.exceptions.OrderIsCanceledException;
import sanity.nil.order.domain.order.exceptions.OrderIsDeletedException;
import sanity.nil.order.domain.order.exceptions.OrderWasDeliveredException;
import sanity.nil.order.domain.order.vo.OrderID;
import sanity.nil.order.util.EntityGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderTest {

    private static OrderProduct product1;
    private static OrderProduct product2;
    private static OrderProduct product3;
    private static Order order;

    @BeforeAll
    public static void initTest() {
        product1 = new OrderProduct(UUID.randomUUID(), "Book", BigDecimal.valueOf(50.2), EntityGenerator.generateActiveDiscount(30), 3);
        product2 = new OrderProduct(UUID.randomUUID(), "Laptop", BigDecimal.valueOf(1345.67), EntityGenerator.generateActiveDiscount(50), 4);
        product3 = new OrderProduct(UUID.randomUUID(), "Headphones", BigDecimal.valueOf(234.89), EntityGenerator.generateExpiredDiscount(30), 1);
        List<OrderProduct> products = new ArrayList<>(List.of(product1, product2, product3));
        OrderStatus orderStatus = OrderStatus.CREATED;
        PaymentMethod paymentMethod = PaymentMethod.CASH;
        PaymentOption paymentOption = PaymentOption.ONE_TRANSFER;
        order = new Order(new OrderID(), UUID.randomUUID(), UUID.randomUUID(), products, orderStatus, paymentMethod, paymentOption);
    }

    @Test
    public void removeProductTest() {
        int initialLength = order.getProducts().size();
        order.removeProduct(product3);

        assertThat(initialLength - 1).isEqualTo(order.getProducts().size());
        assertThat(order.getProducts().contains(product3)).isFalse();
    }

    @Test
    public void addProductTest() {
        OrderProduct product4 = new OrderProduct(UUID.randomUUID(), "Keyboard", BigDecimal.valueOf(47.25), EntityGenerator.generateActiveDiscount(30), 2);
        int initialLength = order.getProducts().size();
        order.addProduct(product4);

        assertThat(order.getProducts().size()).isEqualTo(initialLength + 1);
        assertThat(order.getProducts().contains(product4)).isTrue();
    }

    @Test
    public void preprocessOrderTest() {
        assertThatNoException().isThrownBy(
                () -> order.preprocessOrder()
        );

        order.updateStatus(OrderStatus.CANCELED);
        assertThatExceptionOfType(OrderIsCanceledException.class).isThrownBy(
                () -> order.preprocessOrder()
        );

        order.updateStatus(OrderStatus.DELIVERED);
        assertThatExceptionOfType(OrderWasDeliveredException.class).isThrownBy(
                () -> order.preprocessOrder()
        );

        order.updateStatus(OrderStatus.CREATED);
        order.deleteOrder();
        assertThatExceptionOfType(OrderIsDeletedException.class).isThrownBy(
                () -> order.preprocessOrder()
        );
    }

    @Test
    public void getTotalPriceTest() {
        BigDecimal totalPrice = order.getTotalPrice();
        BigDecimal expectedPrice = BigDecimal.ZERO;
        for (OrderProduct product : order.getProducts()) {
            if (product.getDiscount() == null || !product.getDiscount().isActive()) {
                // if discount isn't active, just add a multiplication of prices for quantity
                expectedPrice = expectedPrice.add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
            } else {
                expectedPrice = expectedPrice.add(product.getTotalPrice());
            }
        }
        assertThat(totalPrice).isEqualTo(expectedPrice);
    }

}
