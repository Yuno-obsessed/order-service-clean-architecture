package sanity.nil.roleservice.presentation.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import sanity.nil.roleservice.application.interfaces.persistence.PermissionReader;
import sanity.nil.roleservice.application.interfaces.persistence.ServiceReader;
import sanity.nil.roleservice.application.query.GetPermissionQuery;
import sanity.nil.roleservice.application.service.PermissionQueryService;
import sanity.nil.roleservice.infrastructure.database.impl.PermissionDAOImpl;
import sanity.nil.roleservice.infrastructure.database.impl.ServiceDAOImpl;
import sanity.nil.roleservice.infrastructure.database.orm.PermissionORM;
import sanity.nil.roleservice.infrastructure.database.orm.ServiceORM;

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
    public PermissionReader permissionReader(PermissionORM permissionORM) {
        return new PermissionDAOImpl(permissionORM);
    }

    @Bean
    public ServiceReader serviceReader(ServiceORM serviceORM) {
        return new ServiceDAOImpl(serviceORM);
    }

    @Bean
    public PermissionQueryService permissionQueryService(PermissionReader permissionReader,
                                                         ServiceReader serviceReader) {
        return new PermissionQueryService(
                new GetPermissionQuery(permissionReader, serviceReader)
        );
    }
    
}
