package sanity.nil.tourservice.application.country.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.tourservice.application.country.dto.CountryDTO;
import sanity.nil.tourservice.application.country.dto.UpdateCountryDTO;
import sanity.nil.tourservice.application.country.interfaces.CountryRepository;
import sanity.nil.tourservice.application.country.interfaces.interactors.UpdateCountryInteractor;
import sanity.nil.tourservice.domain.entity.country.Country;
import sanity.nil.tourservice.domain.entity.country.methods.UpdateCountry;

@RequiredArgsConstructor
public class UpdateCountryInteractorImpl implements UpdateCountryInteractor {

    private final CountryRepository countryRepository;
    private final UpdateCountry updateMethod;

    @Override
    public CountryDTO update(UpdateCountryDTO dto) {
        Country country = updateMethod.update(dto.getId(), dto.getDescription(), dto.getName());
        return countryRepository.updateCountry(country);
    }
}
