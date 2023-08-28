package sanity.nil.onlineshop.application.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.onlineshop.application.order.dto.address.AddressDTO;
import sanity.nil.onlineshop.application.order.dto.product.ProductOrderDTO;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @JsonProperty(value = "order_id", required = true)
    public UUID orderID;

    @JsonProperty(value = "address", required = true)
    public AddressDTO address;

    @JsonProperty(value = "user_id", required = true)
    public UUID userID;

    @JsonProperty(value = "products")
    public List<ProductOrderDTO> products;

    @JsonProperty(value = "payment_method")
    public String paymentMethod;

    @JsonProperty(value = "payment_option")
    public String paymentOption;
}
