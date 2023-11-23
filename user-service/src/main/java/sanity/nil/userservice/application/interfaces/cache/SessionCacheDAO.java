package sanity.nil.userservice.application.interfaces.cache;

import sanity.nil.userservice.application.dto.query.RefreshSessionsQueryDTO;

public interface SessionCacheDAO {

    void saveToken(RefreshSessionsQueryDTO sessionsQueryDTO);
}
