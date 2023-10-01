package sanity.nil.order.application.order.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @JsonProperty(value = "address_id", required = true)
    public UUID id;

    @JsonProperty(value = "address", required = true)
    public String address;
}
