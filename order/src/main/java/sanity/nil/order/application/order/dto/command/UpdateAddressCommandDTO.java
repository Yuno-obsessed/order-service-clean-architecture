package sanity.nil.order.application.order.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressCommandDTO {

    @JsonProperty(value = "address_id", required = true)
    public UUID addressID;

    @JsonProperty(value = "country", required = true)
    public String country;

    @JsonProperty(value = "city", required = true)
    public String city;

    @JsonProperty(value = "street_name", required = true)
    public String streetName;

    @JsonProperty(value = "building_number", required = true)
    public Integer buildingNumber;

    @JsonProperty(value = "postal_code", required = true)
    public String postalCode;
}
