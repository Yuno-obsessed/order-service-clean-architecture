package sanity.nil.tourservice.infrastructure.dao.mapper;

import sanity.nil.tourservice.domain.entity.city.City;
import sanity.nil.tourservice.infrastructure.model.CityModel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CityMapper {

    public static City convertCityModelToEntity(CityModel model) {
        return new City(model.getCityId(), model.getDescription(), model.getName());
    }

    public static List<City> convertListOfCityModelToEntity(List<CityModel> models) {
        return models.stream()
                .filter(Objects::nonNull)
                .map(CityMapper::convertCityModelToEntity)
                .collect(Collectors.toList());
    }
}
