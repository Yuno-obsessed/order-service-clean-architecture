package sanity.nil.onlineshop.application.product.dto.discount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class DiscountDTO {

    @JsonProperty(value = "discount_percent", required = true)
    public Integer discountPercent;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "starts_at", required = true)
    public LocalDateTime startsAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "ends_at", required = true)
    public LocalDateTime endsAt;

    @JsonProperty(value = "expired", required = true)
    public boolean isExpired;


}
