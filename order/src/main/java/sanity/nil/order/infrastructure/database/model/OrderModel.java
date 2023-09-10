package sanity.nil.order.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "orders")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderModel extends BaseModel{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressModel deliveryAddress;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductModel> products;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "payment_option", nullable = false)
    private String paymentOption;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "deleted")
    private boolean deleted;

    public boolean isLogicallyDeleted() {
        return this.deleted && this.getDeletedAt() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
