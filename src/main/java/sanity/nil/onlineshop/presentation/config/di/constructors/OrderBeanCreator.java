package sanity.nil.onlineshop.presentation.config.di.constructors;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sanity.nil.onlineshop.application.order.interactors.CreateAddressInteractorImpl;
import sanity.nil.onlineshop.application.order.interactors.GetAddressInteractorImpl;
import sanity.nil.onlineshop.application.order.interactors.UpdateAddressInteractorImpl;
import sanity.nil.onlineshop.application.order.interfaces.interactors.CreateAddressInteractor;
import sanity.nil.onlineshop.application.order.interfaces.interactors.GetAddressInteractor;
import sanity.nil.onlineshop.application.order.interfaces.interactors.UpdateAddressInteractor;
import sanity.nil.onlineshop.application.order.interfaces.query.AddressDAO;
import sanity.nil.onlineshop.application.order.interfaces.query.AddressReader;
import sanity.nil.onlineshop.domain.order.entity.Address;
import sanity.nil.onlineshop.domain.order.service.AddressService;
import sanity.nil.onlineshop.infrastructure.database.impl.AddressDAOImpl;
import sanity.nil.onlineshop.infrastructure.database.orm.AddressORM;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.onlineshop.infrastructure"),
        @ComponentScan("sanity.nil.onlineshop.domain"),
        @ComponentScan("sanity.nil.onlineshop.application"),
        @ComponentScan("sanity.nil.onlineshop.presentation")
})
public class OrderBeanCreator {

    @Bean
    public AddressDAO addressDAO(AddressORM addressORM) {
        return new AddressDAOImpl(addressORM);
    }

    @Bean
    public AddressReader addressReader(AddressORM addressORM) {
        return new AddressDAOImpl(addressORM);
    }

    @Bean
    public CreateAddressInteractor createAddressInteractor(AddressDAO addressDAO, AddressReader addressReader) {
        return new CreateAddressInteractorImpl(addressDAO, addressReader, new AddressService());
    }

    @Bean
    public UpdateAddressInteractor updateAddressInteractor(AddressDAO addressDAO, AddressReader addressReader) {
        return new UpdateAddressInteractorImpl(addressDAO, addressReader, new AddressService());
    }

    @Bean
    public GetAddressInteractor getAddressInteractor(AddressReader addressReader) {
        return new GetAddressInteractorImpl(addressReader);
    }
}
