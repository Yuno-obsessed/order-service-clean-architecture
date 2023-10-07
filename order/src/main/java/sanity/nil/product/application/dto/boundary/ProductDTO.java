package sanity.nil.product.application.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class ProductDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID id;

}
