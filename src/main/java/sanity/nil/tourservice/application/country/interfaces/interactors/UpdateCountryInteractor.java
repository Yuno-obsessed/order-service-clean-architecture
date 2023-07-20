package sanity.nil.tourservice.application.country.interfaces.interactors;

import sanity.nil.tourservice.application.country.dto.CountryDTO;
import sanity.nil.tourservice.application.country.dto.UpdateCountryDTO;

public interface UpdateCountryInteractor {

    CountryDTO update(UpdateCountryDTO dto);
}
