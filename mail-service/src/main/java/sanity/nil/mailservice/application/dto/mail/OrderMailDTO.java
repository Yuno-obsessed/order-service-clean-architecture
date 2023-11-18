package sanity.nil.mailservice.application.dto.mail;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class OrderMailDTO {

    public UUID id;
    public String country;
    public String city;
    public String street;
    public Integer buildingNumber;
    public String postalCode;
    public BigDecimal totalPrice;
    public List<OrderProductMailDTO> products;
}
