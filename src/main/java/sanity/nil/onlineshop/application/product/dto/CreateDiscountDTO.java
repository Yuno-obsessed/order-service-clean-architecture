package sanity.nil.onlineshop.application.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiscountDTO {

    @JsonProperty(value = "discount_code", required = true)
    private Integer discountCode;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "starts_at", required = true)
    private LocalDateTime startsAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "ends_at", required = true)
    private LocalDateTime endsAt;
}
