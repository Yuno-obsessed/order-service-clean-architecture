package sanity.nil.onlineshop.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "product_statistics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductStatisticsModel {

    @Id
    private UUID productId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @Column(name = "rate")
    private double rate;

    @Column(name = "ratings")
    private Integer ratings;

    @Column(name = "in_wish_list")
    private Integer inWishList;

}
