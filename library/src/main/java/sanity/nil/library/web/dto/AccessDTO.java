package sanity.nil.library.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.library.web.consts.AccessResponse;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessDTO {

    @JsonProperty(value = "access_response", required = true)
    public AccessResponse accessResponse;

    @JsonProperty("access_error")
    public AccessError accessError;

    @JsonProperty("roles")
    public List<String> roles;

    @JsonProperty("user_id")
    public UUID userID;

    public AccessDTO(AccessResponse accessResponse, List<String> roles, UUID userID) {
        this.accessResponse = accessResponse;
        this.roles = roles;
        this.userID = userID;
    }
}
