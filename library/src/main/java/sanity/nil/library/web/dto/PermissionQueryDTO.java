package sanity.nil.library.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PermissionQueryDTO {

    @JsonProperty(value = "roles", required = true)
    public String roles;

    @JsonProperty(value = "uri", required = true)
    public String uri;

    @JsonProperty(value = "verb", required = true)
    public String verb;
}
