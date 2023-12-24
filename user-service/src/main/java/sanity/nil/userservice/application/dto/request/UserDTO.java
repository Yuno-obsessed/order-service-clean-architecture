package sanity.nil.userservice.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonProperty(value = "email")
    public String email;

    @JsonProperty(value = "password")
    public String password;
}
