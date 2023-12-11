package sanity.nil.roleservice.application.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ServiceQueryDTO {

    @JsonProperty(value = "service_name", required = true)
    public String serviceName;

    @JsonProperty(value = "port", required = true)
    public String port;
}
