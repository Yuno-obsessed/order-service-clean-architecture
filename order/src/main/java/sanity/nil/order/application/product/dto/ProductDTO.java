package sanity.nil.order.application.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import sanity.nil.order.application.product.dto.discount.DiscountDTO;
import sanity.nil.order.application.product.dto.statistics.ProductStatisticsDTO;
import sanity.nil.order.application.product.dto.types.ProductTypeDTO;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductDTO {

    @JsonProperty(value = "product_id")
    public UUID id;

    @JsonProperty(value = "description", required = true)
    public String description;

    @JsonProperty(value = "name", required = true)
    public String name;

    @JsonProperty(value = "price", required = true)
    public BigDecimal price;

    @JsonProperty(value = "discount")
    public DiscountDTO discount;

    @JsonProperty(value = "actual_price")
    public BigDecimal actualPrice;

    @JsonProperty(value = "product_type", required = true)
    public ProductTypeDTO productType;

    @JsonProperty(value = "product_statistics")
    public ProductStatisticsDTO productStatistics;

    @JsonProperty(value = "quantity", defaultValue = "0")
    public Integer quantity;

    @JsonProperty(value = "availability")
    public boolean availability;

}
