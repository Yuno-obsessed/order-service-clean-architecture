package sanity.nil.onlineshop.application.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.onlineshop.application.product.dto.discount.CreateDiscountDTO;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO {

    @JsonProperty(value = "product_id")
    public UUID id;

    @JsonProperty(value = "description", required = true)
    public String description;

    @JsonProperty(value = "product_type_id", required = true)
    public Integer typeId;

    @JsonProperty(value = "name", required = true)
    public String name;

    @JsonProperty(value = "price", required = true)
    public BigDecimal price;

    @JsonProperty(value = "discount")
    public CreateDiscountDTO discountDTO;

    @JsonProperty(value = "quantity", defaultValue = "0")
    public Integer quantity;

}
