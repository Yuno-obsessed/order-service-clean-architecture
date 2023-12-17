package sanity.nil.order.application.product.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductSubtypeDTO {

    @JsonProperty(value = "subtype_id", required = true)
    public Integer subtypeId;

    @JsonProperty(value = "subtype_name", required = true)
    public String subtypeName;

}
