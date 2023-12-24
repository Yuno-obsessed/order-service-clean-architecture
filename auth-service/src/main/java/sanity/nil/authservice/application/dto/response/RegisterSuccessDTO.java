package sanity.nil.authservice.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterSuccessDTO {

    @JsonProperty(value = "access_token")
    public String accessToken;
}
