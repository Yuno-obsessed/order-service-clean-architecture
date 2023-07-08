package sanity.nil.tourservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sanity.nil.tourservice.infrastructure.database.dao.country.repository.CountryRepository;
import sanity.nil.tourservice.infrastructure.database.model.Country;
import sanity.nil.tourservice.service.CountryService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public void save(Country model) {
        countryRepository.save(model);
    }

    @Override
    public Country get(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public void update(Country model) {
        if (countryRepository.findById(model.getCountryId()).isPresent()) {
            countryRepository.save(model);
        }

    }

    @Override
    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }
}
