package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product_statistics")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductStatisticsModel {

    @Id
    @Column(name = "product_id")
    private UUID productID;

    @Column(name = "rate", precision = 16, scale = 15)
    private BigDecimal rate;

    @Column(name = "ratings")
    private Integer ratings;

    @Column(name = "in_wish_list")
    private Integer inWishList;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private ProductModel product;

    public ProductStatisticsModel(UUID productID, BigDecimal rate, Integer ratings, Integer inWishList) {
        this.productID = productID;
        this.rate = rate;
        this.ratings = ratings;
        this.inWishList = inWishList;
    }
}
