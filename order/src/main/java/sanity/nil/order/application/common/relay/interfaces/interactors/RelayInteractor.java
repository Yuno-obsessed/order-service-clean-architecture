package sanity.nil.order.application.common.relay.interfaces.interactors;

import java.util.List;
import java.util.UUID;

public interface RelayInteractor {

    List<UUID> sendMessagesToBroker();
}
