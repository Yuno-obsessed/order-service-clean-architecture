package sanity.nil.product.application.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {

    @JsonProperty(value = "image_name")
    public String imageName;

    @JsonProperty(value = "image_location")
    public String imageLocation;
}
