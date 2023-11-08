package sanity.nil.order.infrastructure.database.orm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sanity.nil.order.application.common.application.consts.EventStatus;
import sanity.nil.order.application.common.application.relay.dto.OutboxMessage;
import sanity.nil.order.application.common.domain.event.Event;
import sanity.nil.order.domain.order.events.OrderCreatedEvent;
import sanity.nil.order.infrastructure.database.models.OutboxModel;
import sanity.nil.order.infrastructure.messageBroker.config.RabbitConfig;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OutboxMapper {

    @Autowired
    private RabbitConfig rabbitConfig;

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
        if (event instanceof OrderCreatedEvent) {
            outboxModel.setId(UUID.randomUUID());
            outboxModel.setEventStatus(EventStatus.STATUS_AWAITING.getCode());
            outboxModel.setPayload(event.bytes());
            outboxModel.setExchange(rabbitConfig.getOrderExchange());
            outboxModel.setRoute(rabbitConfig.getOrderCreatedRk());
            outboxModel.setAggregateID(event.uniqueAggregateID());
        }

        return outboxModel;
    }

    public List<OutboxModel> convertEventsToModels(List<Event> events) {
        return events.stream()
                .map(this::convertEventToModel)
                .collect(Collectors.toList());
    }
}
