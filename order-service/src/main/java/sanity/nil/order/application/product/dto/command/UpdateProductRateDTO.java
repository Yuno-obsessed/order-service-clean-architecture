package sanity.nil.order.application.product.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRateDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID productId;

    @JsonProperty(value = "add_rate", required = true)
    public BigDecimal addRate;

}
