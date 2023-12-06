package sanity.nil.userservice.presentation.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import sanity.nil.userservice.application.interfaces.persistence.UserReader;
import sanity.nil.userservice.application.interfaces.security.PasswordEncoder;
import sanity.nil.userservice.application.query.GetUserByEmailAndPasswordQuery;
import sanity.nil.userservice.application.service.UserQueryService;
import sanity.nil.userservice.infrastructure.database.impl.UserDAOImpl;
import sanity.nil.userservice.infrastructure.database.orm.UserORM;
import sanity.nil.userservice.infrastructure.security.PasswordEncoderImpl;

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

    @Bean("myObjectMapper")
    public ObjectMapper myObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public UserReader userReader(UserORM userORM) {
        return new UserDAOImpl(userORM);
    }

    @Bean
    public UserQueryService userService(UserReader userReader, PasswordEncoder passwordEncoder) {
        return new UserQueryService(
                new GetUserByEmailAndPasswordQuery(userReader, passwordEncoder)
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderImpl();
    }
}
