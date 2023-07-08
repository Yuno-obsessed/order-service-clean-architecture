package sanity.nil.tourservice.infrastructure.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.tourservice.infrastructure.database.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> getByUserId(UUID id);

    Optional<User> getByIdentifier(String identifier);
}
