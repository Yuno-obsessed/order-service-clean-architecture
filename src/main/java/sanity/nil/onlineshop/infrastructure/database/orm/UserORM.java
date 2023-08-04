package sanity.nil.onlineshop.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.onlineshop.infrastructure.database.model.UserModel;

import java.util.UUID;

public interface UserORM extends JpaRepository<UserModel, UUID> {

    UserModel getByEmail(String email);
}
