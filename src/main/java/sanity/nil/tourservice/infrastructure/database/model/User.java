package sanity.nil.tourservice.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User implements Cloneable{
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "user_identifier", nullable = false, unique = true, length = 50)
    private String identifier;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                identifier.equals(user.identifier) &&
                email.equals(user.email) &&
                roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, identifier, email, roles);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
