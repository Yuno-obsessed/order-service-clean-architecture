package sanity.nil.authservice.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sanity.nil.authservice.application.consts.AccessError;
import sanity.nil.authservice.application.consts.AccessResponse;
import sanity.nil.authservice.application.dto.command.AccessCommandDTO;
import sanity.nil.authservice.application.dto.response.AccessDTO;
import sanity.nil.authservice.application.interfaces.security.JwtUtils;

@Slf4j
@RequiredArgsConstructor
public class AccessCommand {

    private final JwtUtils jwtUtils;

    public AccessDTO handle(AccessCommandDTO accessCommandDTO) {
        AccessDTO accessDTO = new AccessDTO(AccessResponse.ACCESS_APPROVED);

        if (jwtUtils.validateJwtToken(accessCommandDTO.accessToken)) {
            if (jwtUtils.isTokenExpired(accessCommandDTO.accessToken)) {
                accessDTO.accessResponse = AccessResponse.ACCESS_EXPIRED;
                accessDTO.accessError = AccessError.TOKEN_EXPIRED;
                log.info("Access token = {} is expired.", accessCommandDTO.accessToken);
            } else {
                accessDTO.roles = jwtUtils.getRolesFromJwtToken(accessCommandDTO.accessToken);
                log.info("Access token = {} is valid", accessCommandDTO.accessToken);
            }
        } else {
            accessDTO.accessResponse = AccessResponse.ACCESS_DENIED;
            accessDTO.accessError = AccessError.INVALID_ACCESS_TOKEN;
            log.info("Access token = {} is invalid.", accessCommandDTO.accessToken);
        }

        return accessDTO;
    }
}
