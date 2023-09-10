package sanity.nil.order.presentation.config.di.constructors;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import sanity.nil.order.application.order.interactors.CreateAddressInteractorImpl;
import sanity.nil.order.application.order.interactors.UpdateAddressInteractorImpl;
import sanity.nil.order.application.order.interfaces.interactors.CreateAddressInteractor;
import sanity.nil.order.application.order.interfaces.interactors.GetAddressInteractor;
import sanity.nil.order.application.order.interfaces.interactors.UpdateAddressInteractor;
import sanity.nil.order.application.order.interfaces.persistence.AddressDAO;
import sanity.nil.order.application.order.interfaces.persistence.AddressReader;
import sanity.nil.order.application.order.interactors.GetAddressInteractorImpl;
import sanity.nil.order.domain.order.services.AddressService;
import sanity.nil.order.infrastructure.database.impl.AddressDAOImpl;
import sanity.nil.order.infrastructure.database.orm.AddressORM;
import sanity.nil.order.infrastructure.messageBroker.interactors.OrderTemplateImpl;
import sanity.nil.order.infrastructure.messageBroker.interfaces.BrokerTemplate;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.order.infrastructure"),
        @ComponentScan("sanity.nil.order.domain"),
        @ComponentScan("sanity.nil.order.application"),
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
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter adapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(orderQueue);
        container.setMessageListener(adapter);
        return container;
    }

    @Bean
    public BrokerTemplate brokerTemplate(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        return new OrderTemplateImpl(rabbitTemplate, objectMapper);
    }

    @Bean
    public CreateAddressInteractor createAddressInteractor(AddressDAO addressDAO, AddressReader addressReader) {
        return new CreateAddressInteractorImpl(addressDAO, addressReader, new AddressService());
    }

    @Bean
    public UpdateAddressInteractor updateAddressInteractor(AddressDAO addressDAO, AddressReader addressReader) {
        return new UpdateAddressInteractorImpl(addressDAO, addressReader, new AddressService());
    }

    @Bean
    public GetAddressInteractor getAddressInteractor(AddressReader addressReader) {
        return new GetAddressInteractorImpl(addressReader);
    }
}
