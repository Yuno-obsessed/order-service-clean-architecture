package sanity.nil.library.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public AccessDTO(AccessResponse accessResponse, List<String> roles) {
        this.accessResponse = accessResponse;
        this.roles = roles;
    }
}
