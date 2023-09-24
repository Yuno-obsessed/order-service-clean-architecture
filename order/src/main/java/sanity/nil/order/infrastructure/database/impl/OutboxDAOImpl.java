package sanity.nil.order.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.consts.EventStatus;
import sanity.nil.order.application.relay.dto.OutboxMessage;
import sanity.nil.order.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.domain.common.event.Event;
import sanity.nil.order.infrastructure.database.models.OutboxModel;
import sanity.nil.order.infrastructure.database.orm.OutboxORM;
import sanity.nil.order.infrastructure.database.orm.mapper.OutboxMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OutboxDAOImpl implements OutboxDAO {

    private final OutboxORM outboxORM;

    @Override
    public List<OutboxMessage> getAllNonProcessedMessages() {
        List<OutboxModel> models = outboxORM.getAllByEventStatus(0);
        return OutboxMapper.convertModelsToDTOs(models);
    }

    @Override
    public void updateMessage(List<UUID> ids) {
        List<OutboxModel> modelsToUpdate = outboxORM.findAllByIdIn(ids);
        for (OutboxModel model : modelsToUpdate) {
            model.setEventStatus(1);
        }
        outboxORM.saveAll(modelsToUpdate);
    }

    @Override
    public void updateStatusByAggregateID(UUID aggregateID, EventStatus status) {
       List<OutboxModel> modelsToUpdate = outboxORM.findAllByAggregateID(aggregateID);
       for (OutboxModel model : modelsToUpdate) {
           model.setEventStatus(status.getCode());
       }
       outboxORM.saveAll(modelsToUpdate);
    }

    @Override
    public void addEvents(List<Event> events) {
        outboxORM.saveAll(OutboxMapper.convertEventsToModels(events));
    }
}
