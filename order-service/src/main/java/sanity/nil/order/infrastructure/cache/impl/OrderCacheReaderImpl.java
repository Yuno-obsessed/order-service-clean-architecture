package sanity.nil.order.infrastructure.cache.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryFilters;
import sanity.nil.order.application.order.exceptions.OrderNotFoundException;
import sanity.nil.order.application.order.interfaces.cache.OrderCacheReader;

import java.util.*;

import static sanity.nil.order.application.common.dto.BaseFilters.Order.ASC;

@RequiredArgsConstructor
public class OrderCacheReaderImpl implements OrderCacheReader {

    private final RedisTemplate<String, OrderQueryDTO> redisTemplate;
    @Qualifier("myObjectMapper")
    private final ObjectMapper objectMapper;

    @Override
    public List<OrderQueryDTO> getAllOrders(OrderQueryFilters queryFilters) {
        String pattern = orderKey(null, null);
        Set<String> matchingKeys = redisTemplate.keys(pattern);
        List<OrderQueryDTO> orderQueryDTOList = new ArrayList<>();
        for (String key : matchingKeys) {
            Object object = redisTemplate.opsForValue().get(key);
            orderQueryDTOList.add(objectMapper.convertValue(object, OrderQueryDTO.class));
        }
        int startIndex = Math.min(queryFilters.offset, orderQueryDTOList.size());
        int endIndex = Math.min(queryFilters.limit, orderQueryDTOList.size());
        if (queryFilters.order.equals(ASC)) {
            orderQueryDTOList.sort(Comparator.comparing(OrderQueryDTO::getCreatedAt));
        } else {
            orderQueryDTOList.sort(Comparator.comparing(OrderQueryDTO::getCreatedAt).reversed());
        }
        return orderQueryDTOList.subList(startIndex, endIndex);
    }

    @Override
    public List<OrderQueryDTO> getAllOrdersByUserID(UUID userID, OrderQueryFilters queryFilters) {
        return null;
    }

    @Override
    public OrderQueryDTO getOrder(UUID clientID, UUID orderID) {
        String matchingKey = redisTemplate.keys(orderKey(clientID.toString(), orderID.toString()))
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> OrderNotFoundException.throwEx(orderID)
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

    private String orderKey(String client, String order) {
        return String.format("order:%s:%s",
                client == null || client.isEmpty() ? "*" : client,
                order == null || order.isEmpty() ? "*" : order);

    }
}
