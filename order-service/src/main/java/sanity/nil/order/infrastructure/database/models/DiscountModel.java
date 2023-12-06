package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "discounts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DiscountModel {

    @Id
    @Column(name = "id")
    private UUID discountID;

    @Column(name = "started_at", columnDefinition = "timestamptz")
    private LocalDateTime startedAt;

    @Column(name = "expired_at", columnDefinition = "timestamptz")
    private LocalDateTime expiredAt;

    @Column(name = "percent")
    private Integer percent;

    @Column(name = "active")
    private boolean active;
}
