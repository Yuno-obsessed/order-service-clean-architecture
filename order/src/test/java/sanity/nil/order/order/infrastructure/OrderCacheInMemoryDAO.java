package sanity.nil.order.order.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanity.nil.order.OrderApplication;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.presentation.config.di.SharedBeanCreator;
import sanity.nil.order.presentation.config.di.constructors.OrderBeanCreator;


import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sanity.nil.order.util.EntityGenerator.generateOrderQueryDTO;


@SpringBootTest(classes = OrderApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({
        SpringExtension.class,
})
public class OrderCacheInMemoryDAO {

    @Autowired
    private RedisTemplate<String, OrderQueryDTO> redisTemplate;

    @Test
    public void testGetList() {
        UUID clientID = UUID.randomUUID();
        OrderQueryDTO orderQueryDTO = generateOrderQueryDTO(clientID);
        OrderQueryDTO orderQueryDTO1 = generateOrderQueryDTO(clientID);
        redisTemplate.opsForValue().set(orderKey(orderQueryDTO.userID.toString(), orderQueryDTO.orderID.toString()), orderQueryDTO);
        redisTemplate.opsForValue().set(orderKey(orderQueryDTO1.userID.toString(), orderQueryDTO1.orderID.toString()), orderQueryDTO1);
        List<OrderQueryDTO> orderQueryDTOList = redisTemplate.opsForValue().multiGet(List.of(orderKey(clientID.toString(), null)));
        assertThat(orderQueryDTOList).hasSize(2);
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
