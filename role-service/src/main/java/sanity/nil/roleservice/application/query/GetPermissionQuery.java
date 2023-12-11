package sanity.nil.roleservice.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.roleservice.application.dto.query.PermissionQueryDTO;
import sanity.nil.roleservice.application.dto.query.ServiceQueryDTO;
import sanity.nil.roleservice.application.interfaces.persistence.PermissionReader;
import sanity.nil.roleservice.application.interfaces.persistence.ServiceReader;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetPermissionQuery {

    private final PermissionReader permissionReader;
    private final ServiceReader serviceReader;

    public boolean handle(String port, String uri, String roles) {
        ServiceQueryDTO serviceQueryDTO = serviceReader.getActiveByPort(port, true);
        PermissionQueryDTO permissionQueryDTO = permissionReader.getPermissionByServiceAndURI(serviceQueryDTO.serviceName, uri);
        String[] requestedRoles = roles.split(",");
        String[] permissionRoles = permissionQueryDTO.roles.split(",");
        Set<String> requested = Arrays.stream(requestedRoles).collect(Collectors.toSet());
        Set<String> stored = Arrays.stream(permissionRoles).collect(Collectors.toSet());
        return requested.equals(stored);
    }
}
