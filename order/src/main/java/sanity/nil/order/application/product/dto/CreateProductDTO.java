package sanity.nil.order.application.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.product.dto.discount.CreateDiscountDTO;
import sanity.nil.order.application.product.dto.image.CreateProductImageDTO;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {

    @JsonProperty(value = "description", required = true)
    public String description;

    @JsonProperty(value = "name", required = true)
    public String name;

    @JsonProperty(value = "subtype_id", required = true)
    public Integer subTypeId;

    @JsonProperty(value = "price", required = true)
    public BigDecimal price;

    @JsonProperty(value = "discount", required = true)
    public CreateDiscountDTO discountDTO;

    @JsonProperty(value = "images")
    public CreateProductImageDTO productImage;

    @JsonProperty(value = "quantity", defaultValue = "0")
    public Integer quantity;
}
