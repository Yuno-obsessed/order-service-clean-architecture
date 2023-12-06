package sanity.nil.userservice.domain.user.services;

import sanity.nil.userservice.domain.user.entity.User;
import sanity.nil.userservice.domain.user.events.UserCreatedEvent;
import sanity.nil.userservice.domain.user.vo.Role;
import sanity.nil.userservice.domain.user.vo.UserID;

import java.util.Set;

public class UserService {

    public User create(Set<Role> roles, String firstName, String lastName, String username,
                       String email, String password) {
        User createdUser = new User(new UserID(), roles, firstName, lastName, username, email, false, password);
        createdUser.recordEvent(new UserCreatedEvent());
        return createdUser;
    }
}
