package sanity.nil.onlineshop.application.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductDTO {

    @JsonProperty(value = "product_id")
    private UUID id;

    @JsonProperty(value = "description", required = true)
    private String description;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "price", required = true)
    private BigDecimal price;

    @JsonProperty(value = "discount")
    private DiscountDTO discount;

    @JsonProperty(value = "actual_price")
    private BigDecimal actualPrice;

    @JsonProperty(value = "product_type", required = true)
    private ProductTypeDTO productType;

    @JsonProperty(value = "quantity", defaultValue = "0")
    private Integer quantity;

    @JsonProperty(value = "availability")
    private boolean availability;

}
