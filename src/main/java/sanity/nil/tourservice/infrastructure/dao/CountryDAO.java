package sanity.nil.tourservice.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sanity.nil.tourservice.infrastructure.model.CountryModel;

import java.util.Optional;

public interface CountryDAO extends JpaRepository<CountryModel, Integer> {

    @Query
            ("SELECT c " +
                    "FROM CountryModel c " +
                    "JOIN c.cities city " +
                    "WHERE city.cityId = :cityId"
            )
    Optional<CountryModel> findByCityId(Integer cityId);

    Integer deleteByCountryId(Integer id);
}
