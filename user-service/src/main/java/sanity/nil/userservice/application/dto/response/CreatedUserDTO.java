package sanity.nil.userservice.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreatedUserDTO {

    @JsonProperty(value = "user_id")
    public UUID userID;

    @JsonProperty(value = "roles")
    public List<String> roles;
}
