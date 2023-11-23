package sanity.nil.userservice.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sanity.nil.userservice.infrastructure.database.models.UserModel;

import java.util.Optional;
import java.util.UUID;

public interface UserORM extends JpaRepository<UserModel, UUID> {

    @Query("SELECT u FROM UserModel u " +
            "WHERE u.email = :email " +
            "AND u.deleted = false"
    )
    Optional<UserModel> findByEmail(String email);
}
