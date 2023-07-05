package sanity.nil.tourservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.tourservice.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
