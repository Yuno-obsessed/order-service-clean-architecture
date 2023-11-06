package sanity.nil.order.application.product.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class ProductDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID id;

}
