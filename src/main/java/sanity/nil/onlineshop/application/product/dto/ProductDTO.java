package sanity.nil.onlineshop.application.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
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

    @JsonProperty(value = "quantity", defaultValue = "0")
    private Integer quantity;

    @JsonProperty(value = "availability")
    private boolean availability;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
