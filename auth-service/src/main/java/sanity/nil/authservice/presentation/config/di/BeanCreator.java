package sanity.nil.authservice.presentation.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import sanity.nil.authservice.application.command.AccessCommand;
import sanity.nil.authservice.application.command.LoginCommand;
import sanity.nil.authservice.application.command.LogoutCommand;
import sanity.nil.authservice.application.command.RefreshTokenCommand;
import sanity.nil.authservice.application.dto.boundary.UserDTO;
import sanity.nil.authservice.application.dto.boundary.UserIDQueryDTO;
import sanity.nil.authservice.application.dto.query.RefreshSessionsQueryDTO;
import sanity.nil.authservice.application.interfaces.WebTemplate;
import sanity.nil.authservice.application.interfaces.cache.SessionCacheDAO;
import sanity.nil.authservice.application.interfaces.cache.SessionCacheReader;
import sanity.nil.authservice.application.interfaces.security.FingerprintUtils;
import sanity.nil.authservice.application.interfaces.security.JwtUtils;
import sanity.nil.authservice.application.service.AuthService;
import sanity.nil.authservice.infrastructure.cache.SessionCacheDAOImpl;
import sanity.nil.authservice.infrastructure.security.FingerprintUtilsImpl;
import sanity.nil.authservice.infrastructure.security.JwtUtilsImpl;
import sanity.nil.authservice.infrastructure.web.UserWebTemplate;

@Configuration
public class BeanCreator {

    @Bean("orderRedisTemplate")
    public RedisTemplate<String, RefreshSessionsQueryDTO> redisTemplate(RedisConnectionFactory connectionFactory,
                                                                        @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        RedisTemplate<String, RefreshSessionsQueryDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        return template;
    }

    @Bean("myObjectMapper")
    public ObjectMapper myObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public SessionCacheDAO sessionCacheDAO(RedisTemplate<String, RefreshSessionsQueryDTO> redisTemplate,
                                           @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        return new SessionCacheDAOImpl(redisTemplate, objectMapper);
    }

    @Bean
    public SessionCacheReader sessionCacheReader(RedisTemplate<String, RefreshSessionsQueryDTO> redisTemplate,
                                                 @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        return new SessionCacheDAOImpl(redisTemplate, objectMapper);
    }

    @Bean
    public WebTemplate<UserIDQueryDTO, UserDTO> webTemplate() {
        return new UserWebTemplate();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtilsImpl();
    }

    @Bean
    public FingerprintUtils fingerprintUtils() {
        return new FingerprintUtilsImpl();
    }

    @Bean
    public AuthService authService(SessionCacheDAO sessionCacheDAO,
                                   WebTemplate<UserIDQueryDTO, UserDTO> webTemplate,
                                   JwtUtils jwtUtils,
                                   FingerprintUtils fingerprintUtils) {
        return new AuthService(
                new LoginCommand(sessionCacheDAO, webTemplate, jwtUtils, fingerprintUtils),
                new RefreshTokenCommand(sessionCacheDAO, jwtUtils, fingerprintUtils),
                new AccessCommand(jwtUtils),
                new LogoutCommand(sessionCacheDAO)
        );
    }
}
