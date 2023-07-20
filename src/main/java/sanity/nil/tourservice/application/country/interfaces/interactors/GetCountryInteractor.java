package sanity.nil.tourservice.application.country.interfaces.interactors;

import sanity.nil.tourservice.application.country.dto.CountryDTO;

public interface GetCountryInteractor {

    CountryDTO getById(Integer id);

    CountryDTO getByCityId(Integer id);
}
