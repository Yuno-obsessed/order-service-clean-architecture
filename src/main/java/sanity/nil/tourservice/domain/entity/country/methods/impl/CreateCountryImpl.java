package sanity.nil.tourservice.domain.entity.country.methods.impl;


import sanity.nil.tourservice.domain.entity.country.Country;
import sanity.nil.tourservice.domain.entity.country.methods.CreateCountry;

public class CreateCountryImpl implements CreateCountry {

    @Override
    public Country create(String description, String name) {
        return new Country(0, description, name);
    }
}
