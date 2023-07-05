package sanity.nil.tourservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.tourservice.entity.Image;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
