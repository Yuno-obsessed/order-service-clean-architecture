package sanity.nil.order.infrastructure.database.models;


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

}
