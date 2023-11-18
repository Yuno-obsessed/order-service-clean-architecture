package sanity.nil.mailservice.presentation.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import sanity.nil.mailservice.application.dto.boundary.ProductImageDTO;
import sanity.nil.mailservice.application.dto.mail.OrderMailDTO;
import sanity.nil.mailservice.application.interfaces.MailContentTemplate;
import sanity.nil.mailservice.application.interfaces.MailSender;
import sanity.nil.mailservice.application.interfaces.WebTemplate;
import sanity.nil.mailservice.application.interfaces.persistence.MailDAO;
import sanity.nil.mailservice.infrastructure.database.impl.MailDAOImpl;
import sanity.nil.mailservice.infrastructure.mail.MailSenderImpl;
import sanity.nil.mailservice.infrastructure.mail.template.OrderCreatedMailTemplate;
import sanity.nil.mailservice.infrastructure.messageBroker.config.RabbitConfig;
import sanity.nil.mailservice.infrastructure.web.OrderWebTemplate;
import sanity.nil.mailservice.presentation.consumer.subscribers.MailSubscribers;

import javax.sql.DataSource;
import java.util.UUID;

@EnableRabbit
@Configuration
public class MailBeanCreator {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${application.web.order.base-url}")
    private String orderBaseURL;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean
    public RabbitConfig rabbitConfig() {
        return new RabbitConfig();
    }

    @Bean
    public FanoutExchange orderFanoutExchange(RabbitConfig rabbitConfig) {
        return new FanoutExchange(rabbitConfig.getOrderFanoutExchange(), true, false);
    }

    @Bean
    public Queue orderQueue(RabbitConfig rabbitConfig) {
        return new Queue(rabbitConfig.getMailQueue(), true, false, false);
    }

    @Bean
    public Binding orderCreatedBinding(@Qualifier("orderQueue") Queue queue,
                                       @Qualifier("orderFanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public MailSender customMailSender(JavaMailSender javaMailSender) {
        return new MailSenderImpl(javaMailSender);
    }

    @Bean
    public ITemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine thymeleafTemplateEngine(@Qualifier("thymeleafTemplateResolver") ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mailMessages");
        return messageSource;
    }

    @Bean
    public ObjectMapper myObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public MailDAO mailDAO() {
        return new MailDAOImpl();
    }

    @Bean
    public MailSubscribers mailSubscribers(MailDAO mailDAO,
                                           @Qualifier("customMailSender") MailSender mailSender,
                                           @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        return new MailSubscribers(mailDAO, mailSender, objectMapper);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory myContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(2);
        return factory;
    }

    @Bean
    public WebClient orderWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(orderBaseURL)
                .build();
    }

    @Bean
    public WebTemplate<ProductImageDTO, UUID> orderWebTemplate(WebClient webClient) {
        return new OrderWebTemplate(webClient);
    }

    @Bean
    public MailContentTemplate<OrderMailDTO> orderCreatedMailTemplate(SpringTemplateEngine templateEngine) {
        return new OrderCreatedMailTemplate(templateEngine);
    }

}
