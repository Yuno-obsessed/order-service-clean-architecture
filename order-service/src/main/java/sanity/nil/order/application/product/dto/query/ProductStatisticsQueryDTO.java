package sanity.nil.order.application.product.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.product.dto.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class ProductStatisticsQueryDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID id;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @JsonProperty(value = "rate")
    public BigDecimal rate;

    @JsonProperty(value = "ratings")
    public Integer ratings;

    @JsonProperty(value = "in_wish_list")
    public Integer inWishList;
}
