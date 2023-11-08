package sanity.nil.order.order.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.exceptions.OrderNotFoundException;
import sanity.nil.order.application.order.persistence.OrderCacheDAO;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static sanity.nil.order.util.EntityGenerator.generateOrderQueryDTO;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderCacheInMemoryDAO {

    @Autowired
    private OrderCacheDAO orderCacheDAO;

    private static UUID clientID;
    private static OrderQueryDTO orderQueryDTO1;
    private static OrderQueryDTO orderQueryDTO2;

    @BeforeEach
    public void initTests() {
        clientID = UUID.randomUUID();
        orderQueryDTO1 = generateOrderQueryDTO(clientID);
        orderQueryDTO2 = generateOrderQueryDTO(clientID);
    }

    @Test
    public void testSaveAndGetOrder() {
        orderCacheDAO.saveOrder(orderQueryDTO1);

        OrderQueryDTO existingOrder = orderCacheDAO.getOrder(orderQueryDTO1.orderID);

        assertThat(existingOrder.orderID).isEqualTo(orderQueryDTO1.orderID);
        assertThatExceptionOfType(OrderNotFoundException.class).isThrownBy(
                () -> orderCacheDAO.getOrder(orderQueryDTO2.orderID)
        );
    }

    @Test
    public void testGetOrdersOfClient() {
        orderCacheDAO.saveOrder(orderQueryDTO1);
        orderCacheDAO.saveOrder(orderQueryDTO2);
        List<OrderQueryDTO> orderQueryDTOList = orderCacheDAO.getOrdersOfClient(clientID);

        assertThat(orderQueryDTOList).isNotNull();
        assertThat(orderQueryDTOList.get(0).orderID).isEqualTo(orderQueryDTO1.orderID);
        assertThat(orderQueryDTOList).hasSize(2);
    }

    @Test
    public void testDeleteOrder() {
        orderCacheDAO.saveOrder(orderQueryDTO1);

        assertThat(orderCacheDAO.getOrder(orderQueryDTO1.orderID)).isNotNull();

        orderCacheDAO.deleteOrder(orderQueryDTO1.orderID);

        assertThatExceptionOfType(OrderNotFoundException.class).isThrownBy(
                () -> orderCacheDAO.getOrder(orderQueryDTO1.orderID)
        );
    }

    private String orderKey(String client, String order) {
        if (client == null || client.isEmpty()) {
            client = "*";
        }
        if (order == null || order.isEmpty()) {
            order = "*";
        }

        return String.format("order:%s:%s", client, order);
    }
}
