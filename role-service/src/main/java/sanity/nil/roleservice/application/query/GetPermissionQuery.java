package sanity.nil.roleservice.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.roleservice.application.consts.PermissionError;
import sanity.nil.roleservice.application.dto.query.PermissionQueryDTO;
import sanity.nil.roleservice.application.dto.query.RolePermissionDTO;
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

    public RolePermissionDTO handle(String uri, String verb, String roles) {
        PermissionQueryDTO permissionQueryDTO = permissionReader.getPermissionByURIAndVerb(uri, verb);
        if (roles == null || roles.isEmpty()) {
            roles = "NONE";
        }
        String[] requestedRoles = roles.split(",");
        String[] permissionRoles = permissionQueryDTO.roles.split(",");
        Set<String> requested = new HashSet<>(Arrays.asList(requestedRoles));
        Set<String> stored = new HashSet<>(Arrays.asList(permissionRoles));
        boolean hasAccess = !Collections.disjoint(requested, stored);
        return new RolePermissionDTO(hasAccess, hasAccess ? null : PermissionError.NO_ROLE);
    }
}
