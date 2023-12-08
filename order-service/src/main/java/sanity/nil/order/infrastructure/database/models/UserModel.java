package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "\"users\"", schema = "order_service")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserModel extends BaseModel {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    public UserModel(UUID id, String email, String password) {
        this.setId(id);
        this.email = email;
        this.password = password;
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
