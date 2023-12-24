package sanity.nil.order.application.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UpdatedOrderAddressDTO {

    @JsonProperty(value = "address_id")
    public UUID addressID;

}
