package sanity.nil.roleservice.application.interfaces.persistence;

import sanity.nil.roleservice.application.dto.query.PermissionQueryDTO;

public interface PermissionReader {

    PermissionQueryDTO getPermissionByServiceAndURI(String service, String uri, String method);

}
