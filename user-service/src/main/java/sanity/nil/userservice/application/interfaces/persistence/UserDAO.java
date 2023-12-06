package sanity.nil.userservice.application.interfaces.persistence;

import sanity.nil.userservice.domain.user.entity.User;

public interface UserDAO {

    User createUser(User user);

    User updateUser(User user);

    User deleteUser(User user);
}
