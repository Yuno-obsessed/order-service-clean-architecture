package sanity.nil.onlineshop.application.order.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {

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
