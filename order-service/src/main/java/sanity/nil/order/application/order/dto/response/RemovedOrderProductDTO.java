package sanity.nil.order.application.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class RemovedOrderProductDTO {

    @JsonProperty(value = "order_id")
    public UUID orderID;

    @JsonProperty(value = "user_id")
    public UUID userID;

    @JsonProperty(value = "product_id")
    public UUID productID;
}
