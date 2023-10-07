package sanity.nil.order.infrastructure.cache.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import sanity.nil.order.application.dto.query.OrderQueryDTO;
import sanity.nil.order.application.interfaces.persistence.OrderCacheDAO;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderCacheDAOImpl implements OrderCacheDAO {

    private static final String ORDER_CACHE_KEY = "order_cache";

    private final RedisTemplate<String, OrderQueryDTO> redisTemplate;

    @Override
    public OrderQueryDTO getOrder(UUID id) {
        redisTemplate.opsForHash().get(ORDER_CACHE_KEY, id.toString());
        return null;
    }

    @Override
    public List<OrderQueryDTO> getOrders() {
        return null;
    }

    @Override
    public void saveOrder(OrderQueryDTO orderQueryDTO) {

    }

    @Override
    public void deleteOrder(UUID id) {

    }
}
