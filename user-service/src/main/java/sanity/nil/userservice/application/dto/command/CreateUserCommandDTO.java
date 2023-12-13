package sanity.nil.userservice.application.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateUserCommandDTO {

    @JsonProperty(value = "first_name", required = true)
    public String firstName;

    @JsonProperty(value = "last_name", required = true)
    public String lastName;

    @JsonProperty(value = "username", required = true)
    public String username;

    @JsonProperty(value = "email", required = true)
    public String email;

    @JsonProperty(value = "password", required = true)
    public String password;

    @JsonProperty(value = "repeat_password", required = true)
    public String repeatPassword;

}
