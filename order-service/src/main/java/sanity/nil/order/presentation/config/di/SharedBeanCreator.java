package sanity.nil.order.presentation.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.HandlerInterceptor;
import sanity.nil.order.application.common.dto.AccessCommandDTO;
import sanity.nil.order.application.common.dto.AccessDTO;
import sanity.nil.order.application.common.interfaces.broker.MessageBroker;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.PermissionQueryDTO;
import sanity.nil.order.application.order.interfaces.web.WebTemplate;
import sanity.nil.order.infrastructure.messageBroker.interactors.MessageBrokerImpl;
import sanity.nil.order.infrastructure.storage.config.MinioConfig;
import sanity.nil.order.infrastructure.web.AuthWebTemplate;
import sanity.nil.order.infrastructure.web.RoleWebTemplate;
import sanity.nil.order.presentation.api.exception.request.RequestIdGenerator;
import sanity.nil.order.presentation.api.exception.request.RequestIdHolder;
import sanity.nil.order.presentation.api.exception.request.RequestImpl;
import sanity.nil.order.presentation.api.middleware.AuthenticationFilter;
import sanity.nil.order.presentation.api.middleware.CustomHandlerInterceptor;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("sanity.nil.order.infrastructure.database.orm")
@EnableRabbit
public class SharedBeanCreator {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${application.minio.access-key}")
    private String minioAccessKey;

    @Value("${application.minio.secret-key}")
    private String minioSecretKey;

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
    public RedisTemplate<String, OrderQueryDTO> redisTemplate(RedisConnectionFactory connectionFactory,
                                                       @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        RedisTemplate<String, OrderQueryDTO> template = new RedisTemplate<>();
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
    public MinioConfig minioConfig() {
        return new MinioConfig(minioAccessKey, minioSecretKey);
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

    @Bean
    public MessageBroker messageBroker(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        return new MessageBrokerImpl(rabbitTemplate, objectMapper);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory myContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(2);
        return factory;
    }

//    @Bean
//    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter(WebTemplate<Boolean, PermissionQueryDTO> webTemplate) {
//        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AuthorizationFilter(webTemplate));
//        registrationBean.addUrlPatterns("/api/v1/*");
//        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE+2);
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter(WebTemplate<AccessDTO, AccessCommandDTO> webTemplate) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter(webTemplate));
        registrationBean.addUrlPatterns("/api/v1/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE+1);
        return registrationBean;
    }

    @Bean
    public WebTemplate<Boolean, PermissionQueryDTO> roleWebTemplate() {
        return new RoleWebTemplate();
    }

    @Bean
    public WebTemplate<AccessDTO, AccessCommandDTO> authWebTemplate() {
        return new AuthWebTemplate();
    }

}
