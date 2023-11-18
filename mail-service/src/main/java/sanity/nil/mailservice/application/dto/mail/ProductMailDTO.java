package sanity.nil.mailservice.application.dto.mail;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductMailDTO {

    public String link;
    public String imageURL;
    public String name;
    public Integer discount;
    public double price;
    public double actualPrice;
}
