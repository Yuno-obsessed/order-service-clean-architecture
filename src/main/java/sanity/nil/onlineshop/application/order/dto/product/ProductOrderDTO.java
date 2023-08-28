package sanity.nil.onlineshop.application.order.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID productID;

    @JsonProperty(value = "name", required = true)
    public String name;

    @JsonProperty(value = "price")
    public BigDecimal price;

    @JsonProperty(value = "discount")
    public Integer discount;

    @JsonProperty(value = "actual_price", required = true)
    public BigDecimal actualPrice;
}
