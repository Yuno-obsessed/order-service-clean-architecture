package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ProductModel extends BaseModel {

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "discount_percent")
    private Integer discount;

    @Column(name = "discount_start")
    private LocalDateTime discountStart;

    @Column(name = "discount_end")
    private LocalDateTime discountEnd;

    @Column(name = "price_with_discount", nullable = false, precision = 19, scale = 2)
    private BigDecimal priceWithDiscount;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "availability")
    private boolean availability;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subtype_id")
    private ProductSubtypeModel productSubtype;

    @Column(name = "rate", precision = 16, scale = 15)
    private BigDecimal rate;

    @Column(name = "ratings")
    private Integer ratings;

    @Column(name = "in_wish_list")
    private Integer inWishList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductImageModel> productImages = new ArrayList<>();

    public boolean isDiscountExpired() {
        LocalDateTime now = LocalDateTime.now();
        return this.getDiscountStart().isBefore(now) &&
                this.getDiscountEnd().isAfter(now);
    }

    public boolean isLogicallyDeleted() {
        return this.isDeleted() && this.getDeletedAt() != null;
    }

    public void addProductImage(ProductImageModel productImage) {
        if (productImage != null ) {
            this.productImages.add(productImage);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
