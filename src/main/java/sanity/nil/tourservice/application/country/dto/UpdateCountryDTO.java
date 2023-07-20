package sanity.nil.tourservice.application.country.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import sanity.nil.tourservice.domain.entity.city.City;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateCountryDTO {

    @JsonProperty(value = "country_id", required = true)
    private Integer id;

    @JsonProperty(value = "description", required = true)
    private String description;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "cities")
    private List<City> cities;
}
