package sanity.nil.userservice.application.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginCommand {

    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;
}
