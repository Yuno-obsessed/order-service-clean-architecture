package sanity.nil.order.application.relay.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.interfaces.broker.MessageBroker;
import sanity.nil.order.application.relay.dto.OutboxMessage;
import sanity.nil.order.application.relay.interfaces.interactors.RelayInteractor;
import sanity.nil.order.application.relay.interfaces.persistence.OutboxDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RelayInteractorImpl implements RelayInteractor {

    private final MessageBroker messageBroker;
    private final OutboxDAO outboxDAO;

    @Override
    public void sendMessagesToBroker() {

        List<OutboxMessage> messages = outboxDAO.getAllNonProcessedMessages();
        List<UUID> processedIDs = new ArrayList<>();

        for (OutboxMessage message : messages) {
            messageBroker.publishMessage(message.getExchange(), message.getRoute(), message.getPayload());
            processedIDs.add(message.getId());
        }

        if (!processedIDs.isEmpty()) {
            outboxDAO.updateMessage(processedIDs);
        }
    }
}
