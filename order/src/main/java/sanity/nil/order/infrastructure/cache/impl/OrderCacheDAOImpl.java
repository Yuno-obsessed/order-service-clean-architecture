package sanity.nil.order.infrastructure.cache.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.persistence.OrderCacheDAO;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderCacheDAOImpl implements OrderCacheDAO {

    private final RedisTemplate<String, OrderQueryDTO> redisTemplate;

    @Override
    public OrderQueryDTO getOrder(UUID id) {
        return redisTemplate.opsForValue().get(orderKey(null, id.toString()));
    }

    @Override
    public List<OrderQueryDTO> getOrdersOfClient(UUID clientID) {
        return redisTemplate.opsForValue().multiGet(List.of(orderKey(clientID.toString(), null)));
    }

    @Override
    public void saveOrder(OrderQueryDTO orderQueryDTO) {
        redisTemplate.opsForValue()
                .set(orderKey(orderQueryDTO.userID.toString(),
                        orderQueryDTO.orderID.toString()), orderQueryDTO);
    }

    @Override
    public void deleteOrder(UUID id) {

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
