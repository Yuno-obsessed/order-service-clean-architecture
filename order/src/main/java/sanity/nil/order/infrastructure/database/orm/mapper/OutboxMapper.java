package sanity.nil.order.infrastructure.database.orm.mapper;

import sanity.nil.order.application.common.domain.event.Event;
import sanity.nil.order.infrastructure.database.models.OutboxModel;
import sanity.nil.order.application.common.application.relay.dto.OutboxMessage;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OutboxMapper {

    public static List<OutboxMessage> convertModelsToDTOs(List<OutboxModel> models) {
       return models.stream()
               .map(OutboxMapper::convertModelToDTO)
               .collect(Collectors.toList());
    }

    private static OutboxMessage convertModelToDTO(OutboxModel model) {
        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.setId(model.getId());
        outboxMessage.setExchange(model.getExchange());
        outboxMessage.setRoute(model.getRoute());
        outboxMessage.setPayload(model.getPayload().getBytes(StandardCharsets.UTF_8));
        return outboxMessage;
    }

    private static OutboxModel convertEventToModel(Event event) {
        OutboxModel outboxModel = new OutboxModel();
        outboxModel.setId(UUID.randomUUID());
        outboxModel.setEventStatus(event.getStatus());
        outboxModel.setPayload(Arrays.toString(event.bytes()));
        outboxModel.setExchange(event.getExchange());
        outboxModel.setRoute(event.getRoute());
        outboxModel.setAggregateID(event.uniqueAggregateID());
        outboxModel.setAggregateType(event.getAggregateType());
        return outboxModel;
    }

    public static List<OutboxModel> convertEventsToModels(List<Event> events) {
        return events.stream()
                .map(OutboxMapper::convertEventToModel)
                .collect(Collectors.toList());
    }
}
