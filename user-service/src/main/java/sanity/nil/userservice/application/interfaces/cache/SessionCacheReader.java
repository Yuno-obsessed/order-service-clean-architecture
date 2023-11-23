package sanity.nil.userservice.application.interfaces.cache;

import sanity.nil.userservice.application.dto.query.RefreshSessionsQueryDTO;

import java.util.UUID;

public interface SessionCacheReader {

    RefreshSessionsQueryDTO getByUserID(UUID userID);
}
