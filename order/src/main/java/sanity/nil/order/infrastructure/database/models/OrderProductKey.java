package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderProductKey implements Serializable {

    @Column(name = "order_id")
    private UUID orderID;

    @Column(name = "product_id")
    private UUID productID;

}
