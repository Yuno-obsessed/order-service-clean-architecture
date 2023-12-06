package sanity.nil.order.presentation.config.di.constructors;


import org.springframework.context.annotation.*;
import sanity.nil.order.application.common.interfaces.broker.MessageBroker;
import sanity.nil.order.application.common.relay.interactors.RelayInteractorImpl;
import sanity.nil.order.application.common.relay.interfaces.interactors.RelayInteractor;
import sanity.nil.order.application.common.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.infrastructure.database.impl.OutboxDAOImpl;
import sanity.nil.order.infrastructure.database.orm.OutboxORM;
import sanity.nil.order.infrastructure.database.orm.mapper.OutboxMapper;
import sanity.nil.order.presentation.scheduler.config.SchedulerConfig;

@Configuration
@Import(SchedulerConfig.class)
@ComponentScans(value = {
        @ComponentScan("sanity.nil.order.*"),
})
public class RelayBeanCreator {

    @Bean
    public OutboxDAO outboxDAO(OutboxORM outboxORM, OutboxMapper outboxMapper) {
        return new OutboxDAOImpl(outboxORM, outboxMapper);
    }

    @Bean
    public RelayInteractor relayInteractor(OutboxDAO outboxDAO, MessageBroker messageBroker) {
        return new RelayInteractorImpl(messageBroker, outboxDAO);
    }
}
