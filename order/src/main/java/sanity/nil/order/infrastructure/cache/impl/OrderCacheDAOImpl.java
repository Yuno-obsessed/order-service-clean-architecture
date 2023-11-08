package sanity.nil.order.infrastructure.cache.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.exceptions.OrderNotFoundException;
import sanity.nil.order.application.order.persistence.OrderCacheDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderCacheDAOImpl implements OrderCacheDAO {

    private final RedisTemplate<String, OrderQueryDTO> redisTemplate;

    @Qualifier("myObjectMapper")
    private final ObjectMapper objectMapper;

    @Override
    public OrderQueryDTO getOrder(UUID id) {
        String matchingKey = redisTemplate.keys(orderKey(null, id.toString()))
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> OrderNotFoundException.throwEx(id)
                );
        Object object = redisTemplate.opsForValue().get(matchingKey);
        return objectMapper.convertValue(object, OrderQueryDTO.class);
    }

    @Override
    public List<OrderQueryDTO> getOrdersOfClient(UUID clientID) {
        String pattern = orderKey(clientID.toString(), null);
        Set<String> matchingKeys = redisTemplate.keys(pattern);
        List<OrderQueryDTO> orderQueryDTOList = new ArrayList<>();
        for (String key : matchingKeys) {
            Object object = redisTemplate.opsForValue().get(key);
            orderQueryDTOList.add(objectMapper.convertValue(object, OrderQueryDTO.class));
        }
        return orderQueryDTOList;
    }

    @Override
    public void saveOrder(OrderQueryDTO orderQueryDTO) {
        redisTemplate.opsForValue()
                .set(orderKey(orderQueryDTO.userID.toString(),
                        orderQueryDTO.orderID.toString()), orderQueryDTO);
    }

    @Override
    public void deleteOrder(UUID id) {
        String matchingKey = redisTemplate.keys(orderKey(null, id.toString()))
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> OrderNotFoundException.throwEx(id)
                );
        redisTemplate.opsForValue().getAndDelete(matchingKey);
    }

    private String orderKey(String client, String order) {
        return String.format("order:%s:%s",
                client == null || client.isEmpty() ? "*" : client,
                order == null || order.isEmpty() ? "*" : order);

    }
}
