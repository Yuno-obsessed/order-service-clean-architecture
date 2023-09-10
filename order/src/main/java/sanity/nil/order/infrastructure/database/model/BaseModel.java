package sanity.nil.order.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel {

    @Id
    private UUID id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
       this.createdAt = LocalDateTime.now();
       this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
       this.updatedAt = LocalDateTime.now();
    }
}
