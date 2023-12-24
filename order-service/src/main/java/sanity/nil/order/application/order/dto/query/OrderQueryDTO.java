package sanity.nil.order.application.order.dto.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderQueryDTO {

    @JsonProperty(value = "order_id", required = true)
    public UUID orderID;

    @JsonProperty(value = "address", required = true)
    public AddressQueryDTO address;

    @JsonProperty(value = "user_id", required = true)
    public UUID userID;

    @JsonProperty(value = "products")
    public List<ProductQueryDTO> products;

    @JsonProperty(value = "payment_method")
    public String paymentMethod;

    @JsonProperty(value = "payment_option")
    public String paymentOption;

    @JsonProperty(value = "order_status")
    public String orderStatus;

    @JsonProperty(value = "total_price")
    public BigDecimal totalPrice;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime updatedAt;
}
