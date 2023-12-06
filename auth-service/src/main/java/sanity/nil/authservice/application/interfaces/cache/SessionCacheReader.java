package sanity.nil.authservice.application.interfaces.cache;


import sanity.nil.authservice.application.dto.query.RefreshSessionsQueryDTO;

import java.util.UUID;

public interface SessionCacheReader {

    RefreshSessionsQueryDTO getByUserID(UUID userID);

    RefreshSessionsQueryDTO getByRefreshToken(String refreshToken);
}
