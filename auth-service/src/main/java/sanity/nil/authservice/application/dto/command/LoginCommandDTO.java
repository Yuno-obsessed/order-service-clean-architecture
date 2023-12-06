package sanity.nil.authservice.application.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginCommandDTO {

    @JsonProperty("email")
    public String email;

    @JsonProperty("password")
    public String password;
}
