package sanity.nil.tourservice.application.country.interfaces.interactors;

import sanity.nil.tourservice.application.country.dto.CreateCountryDTO;
import sanity.nil.tourservice.application.country.dto.CountryDTO;

public interface CreateCountryInteractor {

    CountryDTO create(CreateCountryDTO dto);
}
