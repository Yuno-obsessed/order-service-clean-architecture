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
@Table(name = "services", schema = "role_service")
public class ServiceModel {

    @Id
    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "base_url")
    private String baseURL;

    @Column(name = "port")
    private String port;

    @Column(name = "active")
    private boolean active;

    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private LocalDateTime createdAt;
}
