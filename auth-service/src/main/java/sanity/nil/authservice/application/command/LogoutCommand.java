package sanity.nil.authservice.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sanity.nil.authservice.application.dto.query.RefreshSessionsQueryDTO;
import sanity.nil.authservice.application.interfaces.cache.SessionCacheDAO;

@Slf4j
@RequiredArgsConstructor
public class LogoutCommand {

    private final SessionCacheDAO sessionCacheDAO;

    public void handle(String refreshToken) {
        RefreshSessionsQueryDTO refreshSessionsQueryDTO = sessionCacheDAO.deleteByRefreshToken(refreshToken);
        log.info("Deleted refresh token {}", refreshSessionsQueryDTO.refreshToken);
    }
}
