package sanity.nil.userservice.domain.user.entity;

import sanity.nil.userservice.domain.common.aggregate.BaseAggregate;
import sanity.nil.userservice.domain.user.vo.Role;
import sanity.nil.userservice.domain.user.vo.UserID;

import java.util.Set;

public class User extends BaseAggregate {

    private UserID userID;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private boolean emailConfirmed;
    private String password;

    public User(UserID userID, Set<Role> roles, String firstName, String lastName, String username,
                String email, boolean emailConfirmed, String password) {
        this.userID = userID;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.password = password;
    }

    public UserID getUserID() {
        return userID;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public String getPassword() {
        return password;
    }
}
