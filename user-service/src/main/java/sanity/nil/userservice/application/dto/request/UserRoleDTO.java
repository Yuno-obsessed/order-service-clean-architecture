package sanity.nil.userservice.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {

    @JsonProperty(value = "user_id")
    public UUID userID;

    @JsonProperty(value = "role_type")
    public String roleType;
}
