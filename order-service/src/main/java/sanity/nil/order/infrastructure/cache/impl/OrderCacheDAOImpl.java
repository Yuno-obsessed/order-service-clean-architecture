package sanity.nil.order.infrastructure.cache.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.exceptions.OrderNotFoundException;
import sanity.nil.order.application.order.interfaces.cache.OrderCacheDAO;

import java.util.UUID;

@RequiredArgsConstructor
public class OrderCacheDAOImpl implements OrderCacheDAO {

    private final RedisTemplate<String, OrderQueryDTO> redisTemplate;

    @Override
    public void saveOrder(OrderQueryDTO orderQueryDTO) {
        redisTemplate.opsForValue()
                .set(orderKey(orderQueryDTO.userID.toString(),
                        orderQueryDTO.orderID.toString()), orderQueryDTO);
    }

    @Override
    public OrderQueryDTO deleteOrder(UUID clientID, UUID orderID) {
        String matchingKey = redisTemplate.keys(orderKey(clientID.toString(), orderID.toString()))
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> OrderNotFoundException.throwEx(orderID)
                );
        return redisTemplate.opsForValue().getAndDelete(matchingKey);
    }

    private String orderKey(String client, String order) {
        return String.format("order:%s:%s",
                client == null || client.isEmpty() ? "*" : client,
                order == null || order.isEmpty() ? "*" : order);

    }
}
