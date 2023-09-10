package sanity.nil.order.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_types")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProductTypeModel {

    @Id
    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "prefix", nullable = false, length = 3)
    private String prefix;

    @OneToMany(mappedBy = "productType")
    private List<ProductSubtypeModel> subtypes = new ArrayList<>();

    public ProductTypeModel(String typeName, String prefix) {
        this.typeName = typeName;
        this.prefix = prefix;
    }
}
