package sanity.nil.order.application.product.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDTO {

    @JsonProperty(value = "type_name", required = true)
    public String typeName;

    @JsonProperty(value = "type_prefix", required = true)
    public String typePrefix;

    @JsonProperty(value = "product_subtype", required = true)
    public ProductSubtypeDTO productSubtype;
}
