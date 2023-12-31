package sanity.nil.order.presentation.config.di.constructors;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.order.application.common.interfaces.storage.FileStorage;
import sanity.nil.order.application.common.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.application.order.cache.OrderCacheImpl;
import sanity.nil.order.application.order.command.*;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.interfaces.cache.OrderCache;
import sanity.nil.order.application.order.interfaces.cache.OrderCacheDAO;
import sanity.nil.order.application.order.interfaces.cache.OrderCacheReader;
import sanity.nil.order.application.order.interfaces.persistence.AddressDAO;
import sanity.nil.order.application.order.interfaces.persistence.AddressReader;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.order.interfaces.persistence.OrderReader;
import sanity.nil.order.application.order.query.GetAddressQuery;
import sanity.nil.order.application.order.query.GetAllOrdersQuery;
import sanity.nil.order.application.order.query.GetOrderByIDQuery;
import sanity.nil.order.application.order.service.AddressCommandService;
import sanity.nil.order.application.order.service.AddressQueryService;
import sanity.nil.order.application.order.service.OrderCommandService;
import sanity.nil.order.application.order.service.OrderQueryService;
import sanity.nil.order.application.product.interfaces.persistence.ProductImageReader;
import sanity.nil.order.domain.order.services.AddressService;
import sanity.nil.order.domain.order.services.OrderService;
import sanity.nil.order.infrastructure.cache.impl.OrderCacheDAOImpl;
import sanity.nil.order.infrastructure.cache.impl.OrderCacheReaderImpl;
import sanity.nil.order.infrastructure.database.impl.AddressDAOImpl;
import sanity.nil.order.infrastructure.database.impl.OrderDaoImpl;
import sanity.nil.order.infrastructure.database.orm.AddressORM;
import sanity.nil.order.infrastructure.database.orm.OrderORM;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.messageBroker.config.RabbitConfig;
import sanity.nil.order.presentation.consumer.subscribers.OrderSubscribers;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.order.infrastructure"),
        @ComponentScan("sanity.nil.order.domain.order"),
        @ComponentScan("sanity.nil.order.application.order"),
        @ComponentScan("sanity.nil.order.presentation")
})
public class OrderBeanCreator {

    @Bean
    public AddressDAO addressDAO(AddressORM addressORM) {
        return new AddressDAOImpl(addressORM);
    }

    @Bean
    public OrderDAO orderDAO(ProductORM productORM, OrderORM orderORM, AddressORM addressORM) {
        return new OrderDaoImpl(productORM, orderORM, addressORM);
    }

    @Bean
    public OrderReader orderReader(ProductORM productORM, OrderORM orderORM, AddressORM addressORM) {
        return new OrderDaoImpl(productORM, orderORM, addressORM);
    }

    @Bean
    public AddressReader addressReader(AddressORM addressORM) {
        return new AddressDAOImpl(addressORM);
    }

    @Bean
    public FanoutExchange fanoutExchange(RabbitConfig rabbitConfig) {
        return new FanoutExchange(rabbitConfig.getOrderFanoutExchange(), true, false);
    }

    @Bean
    public TopicExchange topicExchange(RabbitConfig rabbitConfig) {
        return new TopicExchange(rabbitConfig.getOrderTopicExchange(), true, false);
    }

    @Bean
    public Queue orderQueue(RabbitConfig rabbitConfig) {
        return new Queue(rabbitConfig.getOrderQueue(), true, false, false);
    }

    @Bean
    public Binding orderCreatedBinding(@Qualifier("orderQueue") Queue queue,
                                       @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding orderRemovedProductBinding(@Qualifier("orderQueue") Queue queue,
                                       @Qualifier("topicExchange") TopicExchange topicExchange,
                                       RabbitConfig rabbitConfig) {
        return BindingBuilder.bind(queue).to(topicExchange).with(rabbitConfig.getOrderRemovedProductRK());
    }

    @Bean
    public Binding orderUpdatedProductQuantity(@Qualifier("orderQueue") Queue queue,
                                            @Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                            RabbitConfig rabbitConfig) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding orderUpdatedAddressBinding(@Qualifier("orderQueue") Queue queue,
                                            @Qualifier("topicExchange") TopicExchange topicExchange,
                                            RabbitConfig rabbitConfig) {
        return BindingBuilder.bind(queue).to(topicExchange).with(rabbitConfig.getOrderUpdatedAddressRK());
    }

    @Bean
    public Binding orderAddedProductBinding(@Qualifier("orderQueue") Queue queue,
                                            @Qualifier("topicExchange") TopicExchange topicExchange,
                                            RabbitConfig rabbitConfig) {
        return BindingBuilder.bind(queue).to(topicExchange).with(rabbitConfig.getOrderAddedProductRK());
    }

    @Bean
    public OrderSubscribers orderSubscribers(OrderCache orderCache,
                                             @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        return new OrderSubscribers(orderCache, objectMapper);
    }

    @Bean
    public RabbitConfig rabbitConfig() {
        return new RabbitConfig();
    }

    @Bean
    public OrderCacheDAO orderCacheDAO(@Qualifier("orderRedisTemplate") RedisTemplate<String, OrderQueryDTO> redisTemplate) {
        return new OrderCacheDAOImpl(redisTemplate);
    }

    @Bean
    public OrderCacheReader orderCacheReader(@Qualifier("orderRedisTemplate") RedisTemplate<String, OrderQueryDTO> redisTemplate,
                                             @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        return new OrderCacheReaderImpl(redisTemplate, objectMapper);
    }

    @Bean
    public OrderCache orderCache(OrderCacheDAO orderCacheDAO) {
        return new OrderCacheImpl(orderCacheDAO);
    }

    @Bean
    public AddressCommandService addressCommandService(AddressDAO addressDAO, IdentityProvider identityProvider) {
        return new AddressCommandService(
                new CreateAddressCommand(addressDAO, new AddressService(), identityProvider),
                new UpdateAddressCommand(addressDAO, new AddressService(), identityProvider)
        );
    }

    @Bean
    public AddressQueryService addressQueryService(AddressReader addressReader) {
        return new AddressQueryService(
                 new GetAddressQuery(addressReader)
        );
    }

    @Bean
    public OrderCommandService orderCommandService(OrderDAO orderDAO, OutboxDAO outboxDAO,
                                                   OrderReader orderReader, FileStorage fileStorage,
                                                   ProductImageReader productImageReader,
                                                   IdentityProvider identityProvider) {
        OrderService orderService = new OrderService();
        return new OrderCommandService(
                new CreateOrderCommand(orderDAO, orderReader, orderService, outboxDAO, identityProvider),
                new AddOrderProductCommand(orderDAO, orderReader, orderService, outboxDAO, fileStorage, productImageReader, identityProvider),
                new UpdateProductQuantityCommand(orderDAO, orderReader, orderService, outboxDAO, identityProvider),
                new RemoveOrderProductCommand(orderDAO, orderReader, orderService, outboxDAO, identityProvider),
                new UpdateOrderAddressCommand(orderDAO, orderReader, orderService, outboxDAO, identityProvider)
        );
    }

    @Bean
    public OrderQueryService orderQueryService(OrderCacheReader orderCacheReader, IdentityProvider identityProvider) {
        return new OrderQueryService(
                new GetAllOrdersQuery(orderCacheReader),
                new GetOrderByIDQuery(orderCacheReader, identityProvider)
        );
    }
}
