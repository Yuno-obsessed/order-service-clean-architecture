package sanity.nil.authservice.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sanity.nil.authservice.application.dto.boundary.RefreshTokenDTO;
import sanity.nil.authservice.application.dto.interactor.RefreshTokenInteractorDTO;
import sanity.nil.authservice.application.dto.query.RefreshSessionsQueryDTO;
import sanity.nil.authservice.application.interfaces.cache.SessionCacheDAO;
import sanity.nil.authservice.application.interfaces.security.FingerprintUtils;
import sanity.nil.authservice.application.interfaces.security.JwtUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class RefreshTokenCommand {

    private final SessionCacheDAO sessionCacheDAO;
    private final JwtUtils jwtUtils;
    private final FingerprintUtils fingerprintUtils;

    public RefreshTokenInteractorDTO handle(RefreshTokenDTO refreshTokenDTO) {
        RefreshSessionsQueryDTO refreshSession = sessionCacheDAO.deleteByRefreshToken(refreshTokenDTO.refreshToken);
        String userID = jwtUtils.getUserIDFromJwtToken(refreshTokenDTO.refreshToken);
        List<String> roles = jwtUtils.getRolesFromJwtToken(refreshTokenDTO.refreshToken);
        String inputFingerprint = fingerprintUtils.generateFingerprint(refreshTokenDTO.userAgent, userID);
        if (!refreshSession.fingerprint.equals(inputFingerprint) &&
                refreshSession.ip.equals(refreshTokenDTO.ip)) {
            log.error("Fingerprint and ip of current request doesn't match with the one existing previously.");
            return null;
        }
        String refreshToken = jwtUtils.generateRefreshToken(userID, roles);
        RefreshSessionsQueryDTO newRefreshSession = new RefreshSessionsQueryDTO(
                UUID.randomUUID(), UUID.fromString(userID), refreshToken, refreshTokenDTO.userAgent,
                inputFingerprint, refreshTokenDTO.ip, LocalDateTime.now()
        );
        sessionCacheDAO.saveToken(newRefreshSession);
        log.info("New refresh token for userID = {} was created.", userID);
        int maxAge = (int) Duration.between(new Date().toInstant(),
                jwtUtils.getExpiryDateFromJwtToken(refreshToken).toInstant()).getSeconds();

        return new RefreshTokenInteractorDTO(jwtUtils.generateAccessToken(userID, roles), refreshToken, maxAge);
    }
}
