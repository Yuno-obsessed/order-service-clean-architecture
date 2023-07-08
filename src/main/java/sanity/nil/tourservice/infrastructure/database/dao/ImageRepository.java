package sanity.nil.tourservice.infrastructure.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.tourservice.infrastructure.database.model.Image;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
