package sanity.nil.userservice.application.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccessCommand {

    @JsonProperty(value = "access_token", required = true)
    public String accessToken;
}
