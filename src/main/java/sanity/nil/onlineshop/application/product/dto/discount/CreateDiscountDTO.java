package sanity.nil.onlineshop.application.product.dto.discount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class CreateDiscountDTO {

    @JsonProperty(value = "discount_code")
    public Integer discountCode;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "starts_at")
    public LocalDateTime startsAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "ends_at")
    public LocalDateTime endsAt;
}
