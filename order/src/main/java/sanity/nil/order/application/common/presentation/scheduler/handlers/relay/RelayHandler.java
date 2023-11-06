package sanity.nil.order.application.common.presentation.scheduler.handlers.relay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sanity.nil.order.application.common.application.relay.interfaces.interactors.RelayInteractor;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelayHandler {

    private final RelayInteractor relayInteractor;

    @Scheduled(fixedRate = 5 * 1000, initialDelay = 15 * 1000)
    public void handle() {
        List<UUID> ids = relayInteractor.sendMessagesToBroker();
        log.info("Messages with ids : " + ids + " sent from relay");
    }
}
