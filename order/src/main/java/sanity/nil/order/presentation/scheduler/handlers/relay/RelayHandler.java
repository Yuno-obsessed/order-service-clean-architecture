package sanity.nil.order.presentation.scheduler.handlers.relay;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import sanity.nil.order.application.relay.interfaces.interactors.RelayInteractor;

@RequiredArgsConstructor
public class RelayHandler {

    private final RelayInteractor relayInteractor;

    @Scheduled(fixedRate = 5 * 1000)
    public void handle() {
        relayInteractor.sendMessagesToBroker();
    }
}
