package sanity.nil.common.application.relay.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OutboxMessage {

    private UUID id;

    private String exchange;

    private String route;

    private byte[] payload;

    public OutboxMessage(String exchange, String route, byte[] payload) {
        this.id = UUID.randomUUID();
        this.exchange = exchange;
        this.route = route;
        this.payload = payload;
    }

}
