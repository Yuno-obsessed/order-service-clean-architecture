package sanity.nil.roleservice.infrastructure.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "permissions", schema = "role_service")
public class PermissionModel {

    @Id
    private int id;

    @Column(name = "service_name", nullable = false, unique = true)
    private String serviceName;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "uri", nullable = false)
    private String uri;

    @Column(name = "verb")
    private String verb;

    @Column(name = "description")
    private String description;

    // role_types divided by commas, to get, use SPLIT(,",")
    @Column(name = "roles")
    private String roles;

    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private LocalDateTime createdAt;

}
