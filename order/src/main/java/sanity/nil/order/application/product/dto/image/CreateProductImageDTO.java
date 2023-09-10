package sanity.nil.order.application.product.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateProductImageDTO {

    @JsonProperty(value = "image_names", required = true)
    public List<String> imageNames;

}
