package sanity.nil.authservice.infrastructure.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sanity.nil.authservice.application.interfaces.security.JwtUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static sanity.nil.authservice.application.consts.JwtExceptions.*;

@Slf4j
@Component
public class JwtUtilsImpl implements JwtUtils {

    @Value("${application.security.jwt.secret}")
    private String jwtSecret;

    @Value("${application.security.jwt.expirationMin}")
    private int jwtExpirationMin;

    @Value("${application.security.jwt.expirationDays}")
    private int jwtExpirationDays;

    public String generateAccessToken(String userID, List<String> roles) {
        String rolesString = String.join(",", roles);
        return Jwts.builder()
                .setSubject(userID)
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(jwtExpirationMin).atZone(ZoneId.systemDefault()).toInstant()))
                .claim("roles", rolesString)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(String userID, List<String> roles) {
        String rolesString = String.join(",", roles);
        return Jwts.builder()
                .setSubject(userID)
                .setAudience(UUID.randomUUID().toString().substring(0, 15))
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusDays(jwtExpirationDays).atZone(ZoneId.systemDefault()).toInstant()))
                .claim("roles", rolesString)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserIDFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Date getExpiryDateFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();
    }

    public List<String> getRolesFromJwtToken(String token) {
        String rolesString = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("roles", String.class);
        return Arrays.stream(rolesString.split(",")).toList();
    }

    public boolean isTokenExpired(String token) {
        return getExpiryDateFromJwtToken(token).before(new Date());
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error(INVALID_JWT_SIGN, e.getMessage());
        } catch (MalformedJwtException e) {
            log.error(INVALID_JWT_TOKEN, e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error(JWT_EXPIRED, e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error(JWT_UNSUPPORTED, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error(JWT_EMPTY, e.getMessage());
        }
        return false;
    }
}
