package sanity.nil.order.presentation.config.di.constructors;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import sanity.nil.order.application.common.application.interfaces.broker.MessageBroker;
import sanity.nil.order.application.order.command.CreateAddressCommand;
import sanity.nil.order.application.order.command.CreateOrderCommand;
import sanity.nil.order.application.order.command.UpdateAddressCommand;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.persistence.AddressDAO;
import sanity.nil.order.application.order.persistence.AddressReader;
import sanity.nil.order.application.order.persistence.OrderCacheDAO;
import sanity.nil.order.application.order.persistence.OrderDAO;
import sanity.nil.order.application.order.query.GetAddressQuery;
import sanity.nil.order.application.order.service.AddressCommandService;
import sanity.nil.order.application.order.service.AddressQueryService;
import sanity.nil.order.application.order.service.OrderCommandService;
import sanity.nil.order.application.common.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.domain.order.services.AddressService;
import sanity.nil.order.domain.order.services.OrderService;
import sanity.nil.order.infrastructure.cache.impl.OrderCacheDAOImpl;
import sanity.nil.order.infrastructure.database.impl.AddressDAOImpl;
import sanity.nil.order.infrastructure.database.impl.OrderDaoImpl;
import sanity.nil.order.infrastructure.database.orm.AddressORM;
import sanity.nil.order.infrastructure.database.orm.OrderORM;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.UserORM;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.order.infrastructure"),
        @ComponentScan("sanity.nil.order.domain.order"),
        @ComponentScan("sanity.nil.order.application.order"),
        @ComponentScan("sanity.nil.order.presentation")
})
public class OrderBeanCreator {

    @Value("${application.order.topic}")
    private String orderTopic;

    @Value("${application.order.queue}")
    private String orderQueue;

    @Value("${application.order.routing-key}")
    private String orderRoutingKey;

    @Bean
    public AddressDAO addressDAO(AddressORM addressORM) {
        return new AddressDAOImpl(addressORM);
    }

    @Bean
    public OrderDAO orderDAO(ProductORM productORM, OrderORM orderORM, AddressORM addressORM, UserORM userORM) {
        return new OrderDaoImpl(productORM, orderORM, addressORM, userORM);
    }

    @Bean
    public AddressReader addressReader(AddressORM addressORM) {
        return new AddressDAOImpl(addressORM);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(orderTopic, true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(orderQueue, true, false, false);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(orderRoutingKey);
    }

    @Bean
    public RedisTemplate<String, OrderQueryDTO> redisTemplate(RedisConnectionFactory redis) {
        RedisTemplate<String, OrderQueryDTO> template = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<OrderQueryDTO> serializer = new Jackson2JsonRedisSerializer<>(OrderQueryDTO.class);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.setConnectionFactory(redis);
        return template;
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter adapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(orderQueue);
        container.setMessageListener(adapter);
        return container;
    }

    @Bean
    public OrderCacheDAO orderCacheDAO(RedisTemplate<String, OrderQueryDTO> redisTemplate) {
        return new OrderCacheDAOImpl(redisTemplate);
    }

    @Bean
    public AddressCommandService addressCommandService(AddressDAO addressDAO) {
        return new AddressCommandService(
                new CreateAddressCommand(addressDAO, new AddressService()),
                new UpdateAddressCommand(addressDAO, new AddressService())
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
                                                   MessageBroker messageBroker) {
        return new OrderCommandService(
                new CreateOrderCommand(orderDAO, outboxDAO, new OrderService(), messageBroker)
        );
    }
}
