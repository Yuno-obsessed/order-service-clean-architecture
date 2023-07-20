package sanity.nil.tourservice.domain.entity.country;


import sanity.nil.tourservice.domain.entity.city.City;

import java.util.List;

public class Country {

    private Integer countryId;

    private String description;

    private String name;

    private List<City> cities;

    public Country(Integer countryId, String description, String name) {
        this.countryId = countryId;
        this.description = description;
        this.name = name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
