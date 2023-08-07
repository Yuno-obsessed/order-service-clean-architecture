package sanity.nil.onlineshop.infrastructure.database.model;


import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;
}
