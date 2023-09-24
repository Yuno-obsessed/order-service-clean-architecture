package sanity.nil.order.application.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    @JsonProperty(value = "address_id", required = true)
    public UUID addressID;

    @JsonProperty(value = "user_id", required = true)
    public UUID userID;

    @JsonProperty(value = "product_ids")
    public List<UUID> productIDs;

    @JsonProperty(value = "payment_method")
    public String paymentMethod;

    @JsonProperty(value = "payment_option")
    public String paymentOption;
}
