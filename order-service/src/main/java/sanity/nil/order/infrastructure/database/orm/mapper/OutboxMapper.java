package sanity.nil.order.infrastructure.database.orm.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sanity.nil.order.application.common.consts.EventStatus;
import sanity.nil.order.application.common.relay.dto.OutboxMessage;
import sanity.nil.order.domain.common.event.Event;
import sanity.nil.order.domain.order.events.OrderCreatedEvent;
import sanity.nil.order.domain.order.events.OrderProductReservedEvent;
import sanity.nil.order.infrastructure.database.models.OutboxModel;
import sanity.nil.order.infrastructure.messageBroker.config.RabbitConfig;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OutboxMapper {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Autowired
    @Qualifier("myObjectMapper")
    private ObjectMapper objectMapper;

    public List<OutboxMessage> convertModelsToDTOs(List<OutboxModel> models) {
       return models.stream()
               .map(this::convertModelToDTO)
               .collect(Collectors.toList());
    }

    private OutboxMessage convertModelToDTO(OutboxModel model) {
        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.setId(model.getId());
        outboxMessage.setExchange(model.getExchange());
        outboxMessage.setRoute(model.getRoute());
        outboxMessage.setPayload(model.getPayload());
        return outboxMessage;
    }

    public OutboxModel convertEventToModel(Event event) {
        OutboxModel outboxModel = new OutboxModel();
        String payload = null;
        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        outboxModel.setPayload(payload);
        outboxModel.setId(event.getBaseEvent().getEventID());
        outboxModel.setEventStatus(EventStatus.STATUS_AWAITING.getCode());
        outboxModel.setAggregateID(event.uniqueAggregateID());
        if (event instanceof OrderCreatedEvent) {
            outboxModel.setExchange(rabbitConfig.getOrderFanoutExchange());
            outboxModel.setRoute(rabbitConfig.getOrderCreatedRK());
        }
        if (event instanceof OrderProductReservedEvent) {
            outboxModel.setExchange(rabbitConfig.getOrderTopicExchange());
            outboxModel.setRoute(rabbitConfig.getOrderAddedProductRK());
        }

        return outboxModel;
    }

    public List<OutboxModel> convertEventsToModels(List<Event> events) {
        return events.stream()
                .map(this::convertEventToModel)
                .collect(Collectors.toList());
    }
}
