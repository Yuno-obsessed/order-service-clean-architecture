package sanity.nil.product.application.dto.boundary;

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

    @JsonProperty(value = "subtype_prefix", required = true)
    public String subtypePrefix;
}
