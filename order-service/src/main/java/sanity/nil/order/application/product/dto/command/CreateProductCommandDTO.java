package sanity.nil.order.application.product.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreateProductCommandDTO {

    @JsonProperty(value = "description", required = true)
    public String description;

    @JsonProperty(value = "name", required = true)
    public String name;

    @JsonProperty(value = "subtype_id", required = true)
    public Integer subTypeID;

    @JsonProperty(value = "price", required = true)
    public BigDecimal price;

    @JsonProperty(value = "discount_id", required = true)
    public UUID discountID;

    @JsonProperty(value = "quantity", defaultValue = "0")
    public Integer quantity;
}
