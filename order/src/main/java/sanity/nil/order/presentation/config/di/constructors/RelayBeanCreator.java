package sanity.nil.order.presentation.config.di.constructors;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import sanity.nil.order.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.infrastructure.database.impl.OutboxDAOImpl;
import sanity.nil.order.infrastructure.database.orm.OutboxORM;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.order.application.relay"),
        @ComponentScan("sanity.nil.order.infrastructure"),
})
public class RelayBeanCreator {

    @Bean
    public OutboxDAO outboxDAO(OutboxORM outboxORM) {
        return new OutboxDAOImpl(outboxORM);
    }
}
