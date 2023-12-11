package sanity.nil.roleservice.application.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PermissionQueryDTO {

    @JsonProperty("sevice_name")
    public String serviceName;

    @JsonProperty("uri")
    public String uri;

    @JsonProperty("method")
    public String method;

    @JsonProperty("roles")
    public String roles;
}
