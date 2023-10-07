package sanity.nil.order.application.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @JsonProperty(value = "order_id", required = true)
    public UUID orderID;

    @JsonProperty(value = "user_id", required = true)
    public UUID userID;

}
