package sanity.nil.order.application.order.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddedOrderProductDTO {

    @JsonProperty(value = "product_id")
    public UUID productID;

    @JsonProperty(value = "name")
    public String name;

    @JsonProperty(value = "price")
    public BigDecimal price;

    @JsonProperty(value = "discount")
    public Integer discount;

    @JsonProperty(value = "actual_price")
    public BigDecimal actualPrice;

    @JsonProperty(value = "image_url")
    public String imageURL;
}
