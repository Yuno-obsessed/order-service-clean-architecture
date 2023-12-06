package sanity.nil.authservice.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.authservice.application.dto.boundary.UserDTO;
import sanity.nil.authservice.application.dto.boundary.UserIDQueryDTO;
import sanity.nil.authservice.application.dto.command.CreateRefreshTokenDTO;
import sanity.nil.authservice.application.dto.interactor.LoginInteractorDTO;
import sanity.nil.authservice.application.dto.query.RefreshSessionsQueryDTO;
import sanity.nil.authservice.application.interfaces.WebTemplate;
import sanity.nil.authservice.application.interfaces.cache.SessionCacheDAO;
import sanity.nil.authservice.application.interfaces.security.FingerprintUtils;
import sanity.nil.authservice.application.interfaces.security.JwtUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class LoginCommand {

    private final SessionCacheDAO sessionDAO;
    private final WebTemplate<UserIDQueryDTO, UserDTO> webTemplate;
    private final JwtUtils jwtUtils;
    private final FingerprintUtils fingerprintUtils;

    public LoginInteractorDTO handle(CreateRefreshTokenDTO createRefreshToken) {
        UserIDQueryDTO userQueryDTO = webTemplate.get(new UserDTO(createRefreshToken.loginCommand.email,
                createRefreshToken.loginCommand.password));
        String accessToken = jwtUtils.generateAccessToken(userQueryDTO.userID, userQueryDTO.roles);
        String refreshToken = jwtUtils.generateRefreshToken(userQueryDTO.userID, userQueryDTO.roles);
        RefreshSessionsQueryDTO sessionsQueryDTO = new RefreshSessionsQueryDTO(
                UUID.randomUUID(), UUID.fromString(userQueryDTO.userID), refreshToken, createRefreshToken.userAgent,
                fingerprintUtils.generateFingerprint(createRefreshToken.userAgent, userQueryDTO.userID),
                createRefreshToken.ip, LocalDateTime.now()
        );
        sessionDAO.saveToken(sessionsQueryDTO);
        int maxAge = (int) Duration.between(new Date().toInstant(),
                jwtUtils.getExpiryDateFromJwtToken(refreshToken).toInstant()).getSeconds();

        return new LoginInteractorDTO(accessToken, refreshToken, maxAge);
    }
}
