package sanity.nil.tourservice.application.country.interfaces;


import sanity.nil.tourservice.application.country.dto.CountryDTO;
import sanity.nil.tourservice.domain.entity.country.Country;

import java.util.Optional;

public interface CountryRepository {

   CountryDTO createCountry(Country country);

   CountryDTO getByCountryId(Integer id);

   CountryDTO getByCityId(Integer id);

   CountryDTO updateCountry(Country country);

   void deleteByCountryId(Integer id);

}
