package sanity.nil.order.infrastructure.messageBroker.interactors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import sanity.nil.common.application.interfaces.broker.MessageBroker;
import sanity.nil.common.application.exceptions.BrokerException;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class MessageBrokerImpl implements MessageBroker {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publishMessage(String exchangeName, String routingKey, byte[] message) throws BrokerException {
        try {
            Message messageAsString = new Message(objectMapper.writeValueAsString(message).getBytes(StandardCharsets.UTF_8));
            rabbitTemplate.send(exchangeName, routingKey, messageAsString);
        } catch (JsonProcessingException | AmqpException e) {
            throw new BrokerException(e);
        }
    }
}
