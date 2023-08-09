package sanity.nil.onlineshop.application.product.dto.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class ProductStatisticsDTO {

    @JsonSerialize(using = BigDecimalSerializer.class)
    @JsonProperty(value = "rate", required = true)
    public BigDecimal rate;

    @JsonProperty(value = "ratings", required = true)
    public Integer ratings;

    @JsonProperty(value = "in_wish_list", required = true)
    public Integer inWishList;

}
