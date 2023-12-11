package sanity.nil.roleservice.application.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionDTO {

    @JsonProperty(value = "role", required = true)
    public String role;

    @JsonProperty(value = "service", required = true)
    public String service;

    @JsonProperty(value = "method", required = true)
    public String method;
}
