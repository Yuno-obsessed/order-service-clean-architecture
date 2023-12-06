package sanity.nil.authservice.application.dto.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserIDQueryDTO {

    @JsonProperty("user_id")
    public String userID;

    @JsonProperty("roles")
    public List<String> roles;

}
