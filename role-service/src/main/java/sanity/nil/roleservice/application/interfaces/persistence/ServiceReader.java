package sanity.nil.roleservice.application.interfaces.persistence;

import sanity.nil.roleservice.application.dto.query.ServiceQueryDTO;

public interface ServiceReader {

    ServiceQueryDTO getActiveByService(String service, Boolean active);
}
