package sanity.nil.authservice.application.interfaces.cache;


import sanity.nil.authservice.application.dto.query.RefreshSessionsQueryDTO;

public interface SessionCacheDAO {

    void saveToken(RefreshSessionsQueryDTO sessionsQueryDTO);

    RefreshSessionsQueryDTO deleteByRefreshToken(String refreshToken);
}
