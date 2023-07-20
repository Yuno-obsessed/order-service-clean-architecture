package sanity.nil.tourservice.infrastructure.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import sanity.nil.tourservice.application.country.dto.CountryDTO;
import sanity.nil.tourservice.application.country.interfaces.CountryRepository;
import sanity.nil.tourservice.domain.entity.country.Country;
import sanity.nil.tourservice.infrastructure.dao.CountryDAO;
import sanity.nil.tourservice.application.country.exceptions.CountryNotFound;
import sanity.nil.tourservice.infrastructure.model.CountryModel;

import static sanity.nil.tourservice.application.country.exceptions.CountryNotFound.getCityIdNotFoundMessage;
import static sanity.nil.tourservice.application.country.exceptions.CountryNotFound.getCountryIdNotFoundMessage;
import static sanity.nil.tourservice.infrastructure.dao.mapper.CountryMapper.convertEntityToModel;
import static sanity.nil.tourservice.infrastructure.dao.mapper.CountryMapper.convertModelToCountryDto;

@RequiredArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private final CountryDAO countryDAO;

    @Override
    public CountryDTO createCountry(Country entity) {
        CountryModel newModel = countryDAO.save(convertEntityToModel(entity));
        return convertModelToCountryDto(newModel);
    }

    @Override
    public CountryDTO getByCountryId(Integer id) {
        return convertModelToCountryDto(countryDAO
                .findById(id)
                .orElseThrow(() ->
                        new CountryNotFound(getCountryIdNotFoundMessage(id))
                )
        );
    }

    @Override
    public CountryDTO getByCityId(Integer id) {
        return convertModelToCountryDto(countryDAO
                .findByCityId(id)
                .orElseThrow(() ->
                        new CountryNotFound(getCityIdNotFoundMessage(id)))
        );
    }

    @Override
    @Transactional
    public CountryDTO updateCountry(Country entity) {
        CountryModel maybeModel = countryDAO
                .findById(entity.getCountryId())
                .orElseThrow(() ->
                        new CountryNotFound(getCountryIdNotFoundMessage(entity.getCountryId()))
        );
        CountryModel model = countryDAO.save(maybeModel
                .withName(entity.getName())
                .withDescription(entity.getDescription())
        );
        return convertModelToCountryDto(model);
    }

    @Override
    @Transactional
    public void deleteByCountryId(Integer id) {
        countryDAO.deleteByCountryId(id);
    }
}
