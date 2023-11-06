package sanity.nil.order.application.common.presentation.config;


import org.springframework.context.annotation.*;
import sanity.nil.order.application.common.application.interfaces.broker.MessageBroker;
import sanity.nil.order.infrastructure.database.orm.OutboxORM;
import sanity.nil.order.application.common.application.relay.interactors.RelayInteractorImpl;
import sanity.nil.order.application.common.application.relay.interfaces.interactors.RelayInteractor;
import sanity.nil.order.application.common.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.infrastructure.database.impl.OutboxDAOImpl;
import sanity.nil.order.application.common.presentation.scheduler.config.SchedulerConfig;

@Configuration
@Import(SchedulerConfig.class)
@ComponentScans(value = {
        @ComponentScan("sanity.nil.common.*"),
})
public class RelayBeanCreator {

    @Bean
    public OutboxDAO outboxDAO(OutboxORM outboxORM) {
        return new OutboxDAOImpl(outboxORM);
    }

    @Bean
    public RelayInteractor relayInteractor(OutboxDAO outboxDAO, MessageBroker messageBroker) {
        return new RelayInteractorImpl(messageBroker, outboxDAO);
    }
}
