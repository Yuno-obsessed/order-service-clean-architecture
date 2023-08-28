package sanity.nil.onlineshop.application.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDTO {

    @JsonProperty(value = "order_id", required = true)
    public UUID orderId;

    @JsonProperty(value = "address_id", required = true)
    public UUID addressID;

    @JsonProperty(value = "user_id", required = true)
    public UUID userID;

    @JsonProperty(value = "user_id")
    public List<UUID> productIDs;

    @JsonProperty(value = "payment_method")
    public String paymentMethod;

    @JsonProperty(value = "payment_option")
    public String paymentOption;
}
