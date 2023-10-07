package sanity.nil.common.application.relay.interfaces.interactors;

import java.util.List;
import java.util.UUID;

public interface RelayInteractor {

    List<UUID> sendMessagesToBroker();
}
