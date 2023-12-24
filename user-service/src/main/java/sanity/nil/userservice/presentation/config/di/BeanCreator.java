package sanity.nil.userservice.presentation.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import sanity.nil.userservice.application.command.CreateUserCommand;
import sanity.nil.userservice.application.interfaces.persistence.RoleReader;
import sanity.nil.userservice.application.interfaces.persistence.UserDAO;
import sanity.nil.userservice.application.interfaces.persistence.UserReader;
import sanity.nil.userservice.application.interfaces.security.PasswordEncoder;
import sanity.nil.userservice.application.query.GetUserByEmailAndPasswordQuery;
import sanity.nil.userservice.application.service.UserCommandService;
import sanity.nil.userservice.application.service.UserQueryService;
import sanity.nil.userservice.domain.user.services.UserService;
import sanity.nil.userservice.infrastructure.database.impl.RoleDAOImpl;
import sanity.nil.userservice.infrastructure.database.impl.UserDAOImpl;
import sanity.nil.userservice.infrastructure.database.orm.RoleORM;
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
    public UserDAO userDAO(UserORM userORM) {
        return new UserDAOImpl(userORM);
    }

    @Bean
    public RoleReader roleReader(RoleORM roleORM) {
        return new RoleDAOImpl(roleORM);
    }

    @Bean
    public UserQueryService userQueryService(UserReader userReader, PasswordEncoder passwordEncoder) {
        return new UserQueryService(
                new GetUserByEmailAndPasswordQuery(userReader, passwordEncoder)
        );
    }

    @Bean
    public UserCommandService userCommandService(UserDAO userDAO, RoleReader roleReader) {
        return new UserCommandService(
                new CreateUserCommand(new UserService(), userDAO, roleReader)
        );
    }

//    @Bean
//    public MessageBroker messageBroker(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
//        return new MessageBrokerImpl(rabbitTemplate, objectMapper);
//    }

    @Bean
    public SimpleRabbitListenerContainerFactory myContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(2);
        return factory;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderImpl();
    }
}
