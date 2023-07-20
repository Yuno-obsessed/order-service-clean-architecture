package sanity.nil.tourservice.application.country.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.tourservice.application.country.dto.CountryDTO;
import sanity.nil.tourservice.application.country.interfaces.CountryRepository;
import sanity.nil.tourservice.application.country.interfaces.interactors.GetCountryInteractor;

@RequiredArgsConstructor
public class GetCountryInteractorImpl implements GetCountryInteractor {

    private final CountryRepository countryRepository;

    @Override
    public CountryDTO getById(Integer id) {
        return countryRepository.getByCountryId(id);
    }

    @Override
    public CountryDTO getByCityId(Integer id) {
        return countryRepository.getByCityId(id);
    }
}
