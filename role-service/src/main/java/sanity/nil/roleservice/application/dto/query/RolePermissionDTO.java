package sanity.nil.roleservice.application.dto.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.roleservice.application.consts.PermissionError;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolePermissionDTO {

    @JsonProperty(value = "access")
    public Boolean hasAccess;

    @JsonProperty("permission_error")
    public PermissionError permissionError;

    public RolePermissionDTO(Boolean hasAccess) {
        this.hasAccess = hasAccess;
    }
}
