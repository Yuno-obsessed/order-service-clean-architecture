package sanity.nil.onlineshop.application.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDTO {

    @JsonProperty(value = "type_id", required = true)
    private Integer typeId;

    @JsonProperty(value = "type", required = true)
    private String type;

    @JsonProperty(value = "prefix", required = true)
    private String prefix;
}
