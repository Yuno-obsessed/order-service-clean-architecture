package sanity.nil.tourservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.tourservice.entity.Tour;

import java.util.UUID;

public interface TourRepository extends JpaRepository<Tour, UUID> {
}
