package sanity.nil.order.infrastructure.database.orm.mapper;

import sanity.nil.order.application.relay.dto.OutboxMessage;
import sanity.nil.order.infrastructure.database.model.OutboxModel;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OutboxMapper {

    public static List<OutboxMessage> convertModelsToDTOs(List<OutboxModel> models) {
        List<OutboxMessage> messages = new ArrayList<>();
        for (OutboxModel model : models)  {
            messages.add(convertModelToDTO(model));
        }
        return messages;
    }

    private static OutboxMessage convertModelToDTO(OutboxModel model) {
        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.setId(model.getId());
        outboxMessage.setExchange(model.getExchange());
        outboxMessage.setRoute(model.getRoute());
        outboxMessage.setPayload(model.getPayload().getBytes(StandardCharsets.UTF_8));
        return outboxMessage;
    }
}
