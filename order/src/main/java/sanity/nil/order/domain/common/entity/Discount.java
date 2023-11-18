package sanity.nil.order.domain.common.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Discount {

    private UUID discountID;
    private Integer percent;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private boolean active;

    public Discount(UUID discountID, Integer percent, LocalDateTime startsAt, LocalDateTime endsAt) {
        this.discountID = discountID;
        this.percent = percent;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.active = isActive();
    }

    public UUID getDiscountID() {
        return discountID;
    }


    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public Integer getPercent() {
        return percent;
    }

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return startsAt.isBefore(now) && endsAt.isAfter(now);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(percent, discount.percent) &&
                Objects.equals(active, discount.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(percent, startsAt, endsAt);
    }
}
