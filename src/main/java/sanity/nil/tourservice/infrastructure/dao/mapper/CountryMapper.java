package sanity.nil.tourservice.infrastructure.dao.mapper;

import sanity.nil.tourservice.application.country.dto.CountryDTO;
import sanity.nil.tourservice.domain.entity.country.Country;
import sanity.nil.tourservice.infrastructure.model.CountryModel;

public class CountryMapper {

    public static CountryModel convertEntityToModel(Country country) {
        CountryModel model = new CountryModel();
        model.setCountryId(country.getCountryId());
        model.setDescription(country.getDescription());
        model.setName(country.getName());
        return model;
    }

    public static CountryDTO convertModelToCountryDto(CountryModel model) {
        return new CountryDTO(model.getCountryId(),
                model.getDescription(), model.getName(),
                CityMapper.convertListOfCityModelToEntity(model.getCities())
        );
    }
}
