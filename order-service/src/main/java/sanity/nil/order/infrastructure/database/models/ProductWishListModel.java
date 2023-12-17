package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "product_wishlist", schema = "order_service")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductWishListModel {

    @Id
    @Column(name = "product_id")
    private UUID productID;

    @Column(name = "rate")
    private String wishList;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private ProductModel product;
}
