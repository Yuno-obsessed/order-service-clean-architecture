package sanity.nil.order.application.product.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductImageInfo {

    @JsonProperty(value = "image_name")
    public String imageName;

    @JsonProperty(value = "image_url")
    public String imageURL;
}
