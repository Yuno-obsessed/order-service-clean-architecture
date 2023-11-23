package sanity.nil.userservice.presentation.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import sanity.nil.userservice.application.dto.query.RefreshSessionsQueryDTO;

import javax.sql.DataSource;

@Configuration
public class BeanCreator {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

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
}
