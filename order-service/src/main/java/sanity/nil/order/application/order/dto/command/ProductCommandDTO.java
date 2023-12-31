package sanity.nil.order.application.order.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductCommandDTO {

    @JsonProperty(value = "product_id", required = true)
    public UUID productID;

    @JsonProperty(value = "quantity", required = true)
    public int quantity;
}
