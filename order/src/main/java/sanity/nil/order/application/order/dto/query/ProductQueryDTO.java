package sanity.nil.order.application.order.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID productID;

    @JsonProperty(value = "name", required = true)
    public String name;

    @JsonProperty(value = "quantity")
    public Integer quantity;

    @JsonProperty(value = "actual_price", required = true)
    public BigDecimal actualPrice;
}
