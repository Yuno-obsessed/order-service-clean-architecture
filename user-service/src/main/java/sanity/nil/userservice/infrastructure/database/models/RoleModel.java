package sanity.nil.userservice.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.*;
import sanity.nil.userservice.application.consts.RoleType;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles", schema = "user_service")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RoleModel {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false, unique = true, length = 20)
    private RoleType roleType;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private LocalDateTime createdAt;
}
