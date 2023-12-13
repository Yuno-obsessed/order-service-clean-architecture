package sanity.nil.roleservice.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.roleservice.application.dto.query.PermissionQueryDTO;
import sanity.nil.roleservice.application.dto.query.ServiceQueryDTO;
import sanity.nil.roleservice.application.interfaces.persistence.PermissionReader;
import sanity.nil.roleservice.application.interfaces.persistence.ServiceReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class GetPermissionQuery {

    private final PermissionReader permissionReader;
    private final ServiceReader serviceReader;

    public boolean handle(String port, String uri, String roles) {
        ServiceQueryDTO serviceQueryDTO = serviceReader.getActiveByPort(port, true);
        PermissionQueryDTO permissionQueryDTO = permissionReader.getPermissionByServiceAndURI(serviceQueryDTO.serviceName, uri);
        if (roles == null || roles.isEmpty()) {
            roles = "NONE";
        }
        String[] requestedRoles = roles.split(",");
        String[] permissionRoles = permissionQueryDTO.roles.split(",");
        Set<String> requested = new HashSet<>(Arrays.asList(requestedRoles));
        Set<String> stored = new HashSet<>(Arrays.asList(permissionRoles));
        return !Collections.disjoint(requested, stored);
    }
}
