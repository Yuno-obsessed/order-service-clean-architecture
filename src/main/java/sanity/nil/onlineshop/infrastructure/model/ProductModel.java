package sanity.nil.onlineshop.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@With
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ProductModel {

    @Id
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "discount", column = @Column(name = "discount_percent")),
            @AttributeOverride(name = "startsAt", column = @Column(name = "discount_start")),
            @AttributeOverride(name = "endsAt", column = @Column(name = "discount_end"))
    })
    private DiscountModel discount;

    @Column(name = "price_with_discount")
    private BigDecimal priceWithDiscount;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
       this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel productModel = (ProductModel) o;
        return productId.equals(productModel.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
