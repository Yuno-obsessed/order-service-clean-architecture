package sanity.nil.tourservice.domain.entity.country.methods;

import sanity.nil.tourservice.domain.entity.country.Country;

public interface UpdateCountry {

    Country update(Integer id, String description, String name);
}
