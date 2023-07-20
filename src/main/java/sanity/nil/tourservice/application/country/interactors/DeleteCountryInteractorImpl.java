package sanity.nil.tourservice.application.country.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.tourservice.application.country.interfaces.CountryRepository;
import sanity.nil.tourservice.application.country.interfaces.interactors.DeleteCountryInteractor;

@RequiredArgsConstructor
public class DeleteCountryInteractorImpl implements DeleteCountryInteractor {

    private final CountryRepository countryRepository;

    @Override
    public Integer delete(Integer id) {
        countryRepository.deleteByCountryId(id);
        return id;
    }
}
