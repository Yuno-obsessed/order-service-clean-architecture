package sanity.nil.tourservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "role_id")
    private Short id;

    @Column(name = "role_type", unique = true, length = 30)
    private String type;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}