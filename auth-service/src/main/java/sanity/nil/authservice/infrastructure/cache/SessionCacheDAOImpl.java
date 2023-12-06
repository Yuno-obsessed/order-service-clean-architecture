package sanity.nil.authservice.infrastructure.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import sanity.nil.authservice.application.dto.query.RefreshSessionsQueryDTO;
import sanity.nil.authservice.application.exceptions.SessionNotFoundException;
import sanity.nil.authservice.application.interfaces.cache.SessionCacheDAO;
import sanity.nil.authservice.application.interfaces.cache.SessionCacheReader;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class SessionCacheDAOImpl implements SessionCacheDAO, SessionCacheReader {

    private final RedisTemplate<String, RefreshSessionsQueryDTO> redisTemplate;

    @Qualifier("myObjectMapper")
    private final ObjectMapper objectMapper;

    @Override
    public void saveToken(RefreshSessionsQueryDTO sessionsQueryDTO) {
        redisTemplate.opsForValue()
                .set(sessionKey(sessionsQueryDTO.userID.toString(),
                        sessionsQueryDTO.refreshToken), sessionsQueryDTO, Duration.ofDays(15));
    }

    @Override
    public RefreshSessionsQueryDTO getByUserID(UUID userID) {
        String matchingKey = Objects.requireNonNull(
                        redisTemplate.keys(sessionKey(userID.toString(), null)))
                .stream()
                .findFirst()
                .orElseThrow(
                        SessionNotFoundException::new
                );
        Object object = redisTemplate.opsForValue().get(matchingKey);
        return objectMapper.convertValue(object, RefreshSessionsQueryDTO.class);
    }

    @Override
    public RefreshSessionsQueryDTO getByRefreshToken(String refreshToken) {
        String matchingKey = Objects.requireNonNull(
                        redisTemplate.keys(sessionKey(null, refreshToken)))
                .stream()
                .findFirst()
                .orElseThrow(
                        SessionNotFoundException::new
                );
        Object object = redisTemplate.opsForValue().get(matchingKey);
        return objectMapper.convertValue(object, RefreshSessionsQueryDTO.class);
    }

    @Override
    public RefreshSessionsQueryDTO deleteByRefreshToken(String refreshToken) {
        String matchingKey = Objects.requireNonNull(
                        redisTemplate.keys(sessionKey(null, refreshToken)))
                .stream()
                .findFirst()
                .orElseThrow(
                        SessionNotFoundException::new
                );
        Object object = redisTemplate.opsForValue().getAndDelete(matchingKey);
        return objectMapper.convertValue(object, RefreshSessionsQueryDTO.class);
    }

    private String sessionKey(String user, String refreshToken) {
        return String.format("session:%s:%s",
                user == null || user.isEmpty() ? "*" : user,
                refreshToken == null || refreshToken.isEmpty() ? "*" : refreshToken);

    }
}
