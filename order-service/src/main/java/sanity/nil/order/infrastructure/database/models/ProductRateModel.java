package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product_rates", schema = "order_service")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRateModel {

    @Id
    @Column(name = "product_id")
    private UUID productID;

    @Column(name = "rate", precision = 16, scale = 15)
    private BigDecimal rate;

    @Column(name = "ratings")
    private Integer ratings;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private ProductModel product;
}
