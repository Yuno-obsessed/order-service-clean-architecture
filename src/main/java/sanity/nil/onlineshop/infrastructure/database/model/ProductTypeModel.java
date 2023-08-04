package sanity.nil.onlineshop.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_types")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProductTypeModel {

    @Id
    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "prefix", nullable = false, length = 3)
    private String prefix;
}
