package sanity.nil.order.application.product.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.product.dto.PriceSerializer;
import sanity.nil.order.application.product.dto.RateSerializer;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class ProductCardQueryDTO {

    @JsonProperty(value = "product_id")
    public UUID productID;

    @JsonProperty(value = "name")
    public String name;

    @JsonSerialize(using = RateSerializer.class)
    @JsonProperty(value = "rate")
    public BigDecimal rate;

    @JsonProperty(value = "ratings")
    public Integer ratings;

    @JsonSerialize(using = PriceSerializer.class)
    @JsonProperty(value = "price")
    public BigDecimal price;

    @JsonProperty(value = "discount")
    public Integer discount;

    @JsonSerialize(using = PriceSerializer.class)
    @JsonProperty(value = "actual_price")
    public BigDecimal actualPrice;

    @JsonProperty(value = "image_url")
    public String imageURL;
}
