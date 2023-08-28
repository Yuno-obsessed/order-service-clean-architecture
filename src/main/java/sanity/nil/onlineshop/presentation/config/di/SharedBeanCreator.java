package sanity.nil.onlineshop.presentation.config.di;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import sanity.nil.onlineshop.presentation.api.middleware.CustomHandlerInterceptor;
import sanity.nil.onlineshop.presentation.api.exception.request.RequestIdGenerator;
import sanity.nil.onlineshop.presentation.api.exception.request.RequestIdHolder;
import sanity.nil.onlineshop.presentation.api.exception.request.RequestImpl;

import javax.sql.DataSource;

@Configuration
@Component
@EnableJpaRepositories("sanity.nil.onlineshop.infrastructure.database.orm")
public class SharedBeanCreator {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public RequestIdHolder requestIdHolder() {
        return new RequestImpl();
    }

    @Bean
    public RequestIdGenerator requestIdGenerator() {
        return new RequestImpl();
    }

    @Bean
    public HandlerInterceptor handlerInterceptor(RequestIdGenerator requestIdGenerator,
                                                 RequestIdHolder requestIdHolder) {
        return new CustomHandlerInterceptor(requestIdHolder, requestIdGenerator);
    }

}
