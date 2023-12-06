package sanity.nil.userservice.application.dto.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserQueryDTO {

    @JsonProperty("user_id")
    public String userID;

    @JsonProperty("first_name")
    public String firstName;

    @JsonProperty("last_name")
    public String lastName;

    @JsonProperty("username")
    public String username;

    @JsonProperty("email")
    public String email;

    @JsonIgnore
    public String password;

    @JsonProperty("email_confirmed")
    public boolean emailConfirmed;

    @JsonProperty("roles")
    public List<String> roles;

}
