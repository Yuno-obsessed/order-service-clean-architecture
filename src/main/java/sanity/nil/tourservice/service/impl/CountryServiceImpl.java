package sanity.nil.tourservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sanity.nil.tourservice.dao.CountryRepository;
import sanity.nil.tourservice.entity.Country;
import sanity.nil.tourservice.service.CountryService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public void save(Country entity) {
        countryRepository.save(entity);
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
    public void update(Country entity) {
        countryRepository.save(entity);
    }

    @Override
    public boolean delete(Integer id) {
        countryRepository.deleteById(id);
        return !countryRepository.existsById(id);
    }
}
