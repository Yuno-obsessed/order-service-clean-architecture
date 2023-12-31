package sanity.nil.order.application.order.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderAddressCommandDTO {

    @JsonProperty(value = "order_id")
    public UUID orderID;

    @JsonProperty(value = "address_id")
    public UUID addressID;
}
