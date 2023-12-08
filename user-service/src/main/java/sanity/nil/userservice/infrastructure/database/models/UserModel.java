package sanity.nil.userservice.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "\"users\"", schema = "user_service")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserModel extends BaseModel {

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "username", unique = true, length = 60)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_type")
    )
    private Set<RoleModel> roles = new HashSet<>();

    public UserModel(UUID id, String firstName, String lastName, String username, String email,
                     boolean emailConfirmed, String password, Set<RoleModel> roles) {
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel that = (UserModel) o;
        return this.getId().equals(that.getId()) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), email);
    }

}
