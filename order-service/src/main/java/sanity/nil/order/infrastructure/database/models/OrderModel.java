package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders", schema = "order_service")
public class OrderModel extends BaseModel {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressModel address;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductModel> products = new ArrayList<>();

    @Column(name = "user_id")
    private UUID userID;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "payment_option", nullable = false)
    private String paymentOption;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    public boolean isLogicallyDeleted() {
        return this.isDeleted() && this.getDeletedAt() != null;
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
