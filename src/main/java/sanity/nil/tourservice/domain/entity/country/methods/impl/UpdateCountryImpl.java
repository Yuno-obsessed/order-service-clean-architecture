package sanity.nil.tourservice.domain.entity.country.methods.impl;

import sanity.nil.tourservice.domain.entity.country.Country;
import sanity.nil.tourservice.domain.entity.country.methods.UpdateCountry;

public class UpdateCountryImpl implements UpdateCountry {

    @Override
    public Country update(Integer id, String description, String name) {
        return new Country(id, description, name);
    }
}
