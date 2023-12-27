package sanity.nil.order.application.order.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderCommandDTO {

    @JsonProperty(value = "address_id", required = true)
    public UUID addressID;

    @JsonProperty(value = "products")
    public List<ProductCommandDTO> products;

    @JsonProperty(value = "payment_method")
    public String paymentMethod;

    @JsonProperty(value = "payment_option")
    public String paymentOption;
}
