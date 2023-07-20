package sanity.nil.tourservice.application.country.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.tourservice.application.country.dto.CreateCountryDTO;
import sanity.nil.tourservice.application.country.dto.CountryDTO;
import sanity.nil.tourservice.application.country.interfaces.CountryRepository;
import sanity.nil.tourservice.application.country.interfaces.interactors.CreateCountryInteractor;
import sanity.nil.tourservice.domain.entity.country.Country;
import sanity.nil.tourservice.domain.entity.country.methods.CreateCountry;

@RequiredArgsConstructor
public class CreateCountryInteractorImpl implements CreateCountryInteractor {

    private final CountryRepository countryRepository;
    private final CreateCountry createMethod;


    @Override
    public CountryDTO create(CreateCountryDTO dto) {
        Country country = createMethod.create(dto.getDescription(), dto.getName());
        return countryRepository.createCountry(country);
    }
}
