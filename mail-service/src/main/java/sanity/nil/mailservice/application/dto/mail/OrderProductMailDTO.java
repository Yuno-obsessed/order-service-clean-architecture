package sanity.nil.mailservice.application.dto.mail;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class OrderProductMailDTO {

    public String link;
    public String imageURL;
    public String name;
    public BigDecimal price;
    public Integer quantity;
}
