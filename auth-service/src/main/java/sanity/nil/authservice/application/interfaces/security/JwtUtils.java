package sanity.nil.authservice.application.interfaces.security;

import java.util.Date;
import java.util.List;

public interface JwtUtils {

    String generateAccessToken(String userID, List<String> roles);

    String generateRefreshToken(String userID, List<String> roles);

    Date getExpiryDateFromJwtToken(String token);

    String getUserIDFromJwtToken(String token);

    List<String> getRolesFromJwtToken(String token);

    boolean isTokenExpired(String token);

    boolean validateJwtToken(String authToken);
}
