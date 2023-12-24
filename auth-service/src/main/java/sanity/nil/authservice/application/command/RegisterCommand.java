package sanity.nil.authservice.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.authservice.application.dto.boundary.UserCreateCommandDTO;
import sanity.nil.authservice.application.dto.boundary.UserCreatedDTO;
import sanity.nil.authservice.application.dto.command.CreateAuthorityCommandDTO;
import sanity.nil.authservice.application.dto.interactor.RegisterInteractorDTO;
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
public class RegisterCommand {

    private final SessionCacheDAO sessionDAO;
    private final WebTemplate<UserCreatedDTO, UserCreateCommandDTO> webTemplate;
    private final JwtUtils jwtUtils;
    private final FingerprintUtils fingerprintUtils;

    public RegisterInteractorDTO handle(CreateAuthorityCommandDTO createAuthority) {
        UserCreatedDTO userCreatedDTO = webTemplate.get(new UserCreateCommandDTO(createAuthority.registerCommandDTO.email,
                createAuthority.registerCommandDTO.password));
        String accessToken = jwtUtils.generateAccessToken(userCreatedDTO.userID, userCreatedDTO.roles);
        String refreshToken = jwtUtils.generateRefreshToken(userCreatedDTO.userID, userCreatedDTO.roles);
        RefreshSessionsQueryDTO sessionsQueryDTO = new RefreshSessionsQueryDTO(
                UUID.randomUUID(), UUID.fromString(userCreatedDTO.userID), refreshToken, createAuthority.userAgent,
                fingerprintUtils.generateFingerprint(createAuthority.userAgent, userCreatedDTO.userID),
                createAuthority.ip, LocalDateTime.now()
        );
        sessionDAO.saveToken(sessionsQueryDTO);
        int maxAge = (int) Duration.between(new Date().toInstant(),
                jwtUtils.getExpiryDateFromJwtToken(refreshToken).toInstant()).getSeconds();

        return new RegisterInteractorDTO(accessToken, refreshToken, maxAge);
    }
}
