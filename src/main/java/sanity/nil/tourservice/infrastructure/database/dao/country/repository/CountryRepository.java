package sanity.nil.tourservice.infrastructure.database.dao.country.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.tourservice.infrastructure.database.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
