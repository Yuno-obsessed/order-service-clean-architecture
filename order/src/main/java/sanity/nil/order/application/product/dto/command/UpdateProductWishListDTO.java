package sanity.nil.order.application.product.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductWishListDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID productId;

    @JsonProperty(value = "add_in_wish_list", defaultValue = "false")
    public boolean addInWishList;
}
