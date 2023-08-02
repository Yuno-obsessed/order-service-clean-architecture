package sanity.nil.onlineshop.infrastructure.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscountModel {

    private Integer discount;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    @Transient
    private boolean isExpired;
}
