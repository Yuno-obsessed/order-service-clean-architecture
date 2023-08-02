package sanity.nil.onlineshop.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.onlineshop.infrastructure.model.UserModel;

import java.util.UUID;

public interface UserDAO extends JpaRepository<UserModel, UUID> {

    UserModel getByEmail(String email);
}
