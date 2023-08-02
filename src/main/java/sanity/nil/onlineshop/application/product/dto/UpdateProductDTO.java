package sanity.nil.onlineshop.application.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateProductDTO {

    @JsonProperty(value = "product_id")
    private UUID id;

    @JsonProperty(value = "description", required = true)
    private String description;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "price", required = true)
    private BigDecimal price;

    @JsonProperty(value = "discount_code", defaultValue = "3")
    private Integer discountCode;

    @JsonProperty(value = "quantity", defaultValue = "0")
    private Integer quantity;

}
