package sanity.nil.library.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AccessCommandDTO {

    @JsonProperty(value = "access_token", required = true)
    public String accessToken;
}
