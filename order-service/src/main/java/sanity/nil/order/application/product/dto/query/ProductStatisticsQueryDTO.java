package sanity.nil.order.application.product.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.product.dto.RateSerializer;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class ProductStatisticsQueryDTO {

    @JsonSerialize(using = RateSerializer.class)
    @JsonProperty(value = "rate")
    public BigDecimal rate;

    @JsonProperty(value = "ratings")
    public Integer ratings;

    @JsonProperty(value = "in_wish_list")
    public Integer inWishList;
}
