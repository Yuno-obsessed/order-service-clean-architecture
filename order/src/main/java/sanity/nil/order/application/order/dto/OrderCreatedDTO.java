package sanity.nil.order.application.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.order.dto.address.AddressDTO;
import sanity.nil.order.application.order.dto.product.ProductOrderDTO;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedDTO {

    @JsonProperty(value = "order_id", required = true)
    public UUID orderID;

    @JsonProperty(value = "user_id", required = true)
    public UUID userID;

}
