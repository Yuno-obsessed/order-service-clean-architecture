package sanity.nil.order.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_subtypes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductSubtypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtype_id")
    private Integer productSubtypeId;

    @Column(name = "subtype_name")
    private String subtypeName;

    @Column(name = "subtype_prefix")
    private String subtypePrefix;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_name")
    private ProductTypeModel productType;

}
