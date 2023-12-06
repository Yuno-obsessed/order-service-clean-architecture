package sanity.nil.userservice.application.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserAuthQueryDTO {

    @JsonProperty("user_id")
    public String userID;

    @JsonProperty("roles")
    public List<String> roles;
}
