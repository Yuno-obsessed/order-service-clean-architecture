package sanity.nil.tourservice.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer productId;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
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
