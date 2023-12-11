package sanity.nil.order.application.order.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PermissionQueryDTO {

    @JsonProperty(value = "roles", required = true)
    public String roles;

    @JsonProperty(value = "port", required = true)
    public String port;

    @JsonProperty(value = "uri", required = true)
    public String uri;
}
