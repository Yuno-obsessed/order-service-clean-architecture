package sanity.nil.order.application.product.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.product.dto.RateSerializer;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class ProductStatisticsDTO {

    @JsonSerialize(using = RateSerializer.class)
    @JsonProperty(value = "rate", required = true)
    public BigDecimal rate;

    @JsonProperty(value = "ratings", required = true)
    public Integer ratings;

    @JsonProperty(value = "in_wish_list", required = true)
    public Integer inWishList;
}
