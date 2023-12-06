package sanity.nil.order.infrastructure.database.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductImageModel {

    @Id
    private UUID id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "product_id")
    private UUID productId;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public ProductImageModel(UUID id, String imageName, String bucketName) {
        this.id = id;
        this.imageName = imageName;
        this.bucketName = bucketName;
    }
}
