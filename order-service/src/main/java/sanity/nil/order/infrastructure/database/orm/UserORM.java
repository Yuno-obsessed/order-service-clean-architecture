package sanity.nil.order.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.order.infrastructure.database.models.UserModel;

import java.util.UUID;

public interface UserORM extends JpaRepository<UserModel, UUID> {

    UserModel getByEmail(String email);
}
