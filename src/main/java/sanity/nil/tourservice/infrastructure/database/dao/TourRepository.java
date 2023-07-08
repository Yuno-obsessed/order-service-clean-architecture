package sanity.nil.tourservice.infrastructure.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.tourservice.infrastructure.database.model.Tour;

import java.util.UUID;

public interface TourRepository extends JpaRepository<Tour, UUID> {
}
