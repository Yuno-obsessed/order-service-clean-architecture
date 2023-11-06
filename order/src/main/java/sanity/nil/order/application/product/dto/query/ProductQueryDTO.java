package sanity.nil.order.application.product.dto.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.product.dto.boundary.ProductImageDTO;
import sanity.nil.order.application.product.dto.boundary.DiscountDTO;
import sanity.nil.order.application.product.dto.boundary.ProductStatisticsDTO;
import sanity.nil.order.application.product.dto.boundary.ProductTypeDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductQueryDTO {

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

//    @JsonProperty(value = "actual_price")
//    public BigDecimal actualPrice;

    @JsonProperty(value = "quantity", defaultValue = "0")
    public Integer quantity;

    @JsonProperty(value = "availability")
    public boolean availability;

    @JsonProperty(value = "product_type")
    public ProductTypeDTO productType;

    @JsonProperty(value = "product_statistics")
    public ProductStatisticsDTO productStatistics;

    @JsonProperty(value = "images")
    public List<ProductImageDTO> productImages;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime updatedAt;
}
