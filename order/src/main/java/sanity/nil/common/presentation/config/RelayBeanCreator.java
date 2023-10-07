package sanity.nil.common.presentation.config;


import org.springframework.context.annotation.*;
import sanity.nil.common.application.interfaces.broker.MessageBroker;
import sanity.nil.common.application.relay.interactors.RelayInteractorImpl;
import sanity.nil.common.application.relay.interfaces.interactors.RelayInteractor;
import sanity.nil.common.application.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.common.infrastructure.database.dao.OutboxDAOImpl;
import sanity.nil.common.infrastructure.database.orm.OutboxORM;
import sanity.nil.common.presentation.scheduler.config.SchedulerConfig;

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
