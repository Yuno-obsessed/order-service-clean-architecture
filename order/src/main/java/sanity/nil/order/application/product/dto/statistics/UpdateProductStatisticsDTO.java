package sanity.nil.order.application.product.dto.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductStatisticsDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID productId;

    @JsonProperty(value = "add_rate", required = true)
    public BigDecimal addRate;

    @JsonProperty(value = "add_in_wish_list", defaultValue = "false")
    public boolean addInWishList;
}
