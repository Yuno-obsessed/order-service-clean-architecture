package sanity.nil.order.infrastructure.messageBroker.interactors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import sanity.nil.order.infrastructure.messageBroker.config.RabbitConfig;
import sanity.nil.order.infrastructure.messageBroker.interfaces.BrokerTemplate;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class OrderTemplateImpl implements BrokerTemplate {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publishMessage(byte[] message) {
        Message messageAsString = null;
        try {
            messageAsString = new Message(objectMapper.writeValueAsString(message).getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        try {
            rabbitTemplate.send(RabbitConfig.ORDER_TOPIC, RabbitConfig.ORDER_ROUT_KEY, messageAsString);
        } catch (AmqpException e) {
            log.error(e.getMessage());
        }
    }
}
