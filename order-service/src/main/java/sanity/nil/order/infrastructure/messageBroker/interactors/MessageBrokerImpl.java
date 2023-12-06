package sanity.nil.order.infrastructure.messageBroker.interactors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import sanity.nil.order.application.common.exceptions.BrokerException;
import sanity.nil.order.application.common.interfaces.broker.MessageBroker;

@Slf4j
@RequiredArgsConstructor
public class MessageBrokerImpl implements MessageBroker {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publishMessage(String exchangeName, String routingKey, String message) {
        try {
            MessageProperties properties = new MessageProperties();
            properties.setContentType("application/json");
            Message messageAsString = new Message(message.getBytes(), properties);
            rabbitTemplate.convertAndSend(exchangeName, routingKey, messageAsString);
        } catch (AmqpException e) {
            throw new BrokerException(e);
        }
    }
}
