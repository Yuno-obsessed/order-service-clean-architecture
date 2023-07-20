package sanity.nil.tourservice.domain.entity.country.methods;

import sanity.nil.tourservice.domain.entity.country.Country;

public interface CreateCountry {

    Country create(String description, String name);
}
