package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "outbox")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutboxModel extends BaseModel {

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "route")
    private String route;

    @Column(name = "payload", columnDefinition = "bytea")
    private byte[] payload;

    @Column(name = "aggregate_id")
    private UUID aggregateID;

    @Column(name = "event_status")
    private int eventStatus;
}
