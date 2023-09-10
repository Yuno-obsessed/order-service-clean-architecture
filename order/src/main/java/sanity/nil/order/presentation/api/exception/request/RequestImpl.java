package sanity.nil.order.presentation.api.exception.request;

import org.slf4j.MDC;

import java.util.Optional;
import java.util.UUID;

public class RequestImpl implements RequestIdGenerator, RequestIdHolder{
    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }

    @Override
    public Optional<UUID> get() {
        return Optional.ofNullable(MDC.get("request_id")).map(UUID::fromString);
    }

    @Override
    public void save(UUID id) {
        MDC.remove("request_id");
        MDC.put("request_id", id.toString());
    }
}
