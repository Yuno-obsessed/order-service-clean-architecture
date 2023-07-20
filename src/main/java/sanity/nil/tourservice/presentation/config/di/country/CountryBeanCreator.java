package sanity.nil.tourservice.presentation.config.di.country;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sanity.nil.tourservice.application.country.interactors.CreateCountryInteractorImpl;
import sanity.nil.tourservice.application.country.interactors.DeleteCountryInteractorImpl;
import sanity.nil.tourservice.application.country.interactors.GetCountryInteractorImpl;
import sanity.nil.tourservice.application.country.interactors.UpdateCountryInteractorImpl;
import sanity.nil.tourservice.application.country.interfaces.CountryRepository;
import sanity.nil.tourservice.application.country.interfaces.interactors.CreateCountryInteractor;
import sanity.nil.tourservice.application.country.interfaces.interactors.DeleteCountryInteractor;
import sanity.nil.tourservice.application.country.interfaces.interactors.GetCountryInteractor;
import sanity.nil.tourservice.application.country.interfaces.interactors.UpdateCountryInteractor;
import sanity.nil.tourservice.domain.entity.country.methods.CreateCountry;
import sanity.nil.tourservice.domain.entity.country.methods.UpdateCountry;
import sanity.nil.tourservice.domain.entity.country.methods.impl.CreateCountryImpl;
import sanity.nil.tourservice.domain.entity.country.methods.impl.UpdateCountryImpl;
import sanity.nil.tourservice.infrastructure.dao.CountryDAO;
import sanity.nil.tourservice.infrastructure.impl.CountryRepositoryImpl;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.tourservice.infrastructure"),
        @ComponentScan("sanity.nil.tourservice.domain"),
        @ComponentScan("sanity.nil.tourservice.application"),
        @ComponentScan("sanity.nil.tourservice.presentation")
})
@EnableJpaRepositories("sanity.nil.tourservice.infrastructure.dao")
public class CountryBeanCreator {

    @Bean
    public CountryRepository countryRepository(CountryDAO countryDAO) {
        return new CountryRepositoryImpl(countryDAO);
    }

    @Bean
    public CreateCountry createCountry() {
       return new CreateCountryImpl();
    }

    @Bean
    public UpdateCountry updateCountry() {
       return new UpdateCountryImpl();
    }

    @Bean
    public CreateCountryInteractor createCountryInteractor(CountryRepository countryRepository, CreateCountry createCountry) {
      return new CreateCountryInteractorImpl(countryRepository, createCountry);
    }

    @Bean
    public UpdateCountryInteractor updateCountryInteractor(CountryRepository countryRepository, UpdateCountry updateCountry) {
       return new UpdateCountryInteractorImpl(countryRepository, updateCountry);
    }

    @Bean
    public GetCountryInteractor getCountryInteractor(CountryRepository countryRepository) {
      return new GetCountryInteractorImpl(countryRepository);
    }

    @Bean
    public DeleteCountryInteractor deleteCountryInteractor(CountryRepository countryRepository) {
        return new DeleteCountryInteractorImpl(countryRepository);
    }

}
