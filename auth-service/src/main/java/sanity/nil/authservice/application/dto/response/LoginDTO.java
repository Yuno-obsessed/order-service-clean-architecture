package sanity.nil.authservice.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginDTO {

    @JsonProperty(value = "access_token")
    public String accessToken;
}
