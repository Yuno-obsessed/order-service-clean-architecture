package sanity.nil.mailservice.application.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.mailservice.application.consts.MailType;
import sanity.nil.mailservice.application.dto.boundary.ProductImageDTO;
import sanity.nil.mailservice.application.dto.mail.MailDTO;
import sanity.nil.mailservice.application.dto.mail.OrderMailDTO;
import sanity.nil.mailservice.application.dto.mail.OrderProductMailDTO;
import sanity.nil.mailservice.application.dto.mail.ProductMailDTO;
import sanity.nil.mailservice.application.interfaces.MailContentTemplate;
import sanity.nil.mailservice.application.interfaces.MailSender;
import sanity.nil.mailservice.application.interfaces.WebTemplate;
import sanity.nil.mailservice.application.interfaces.persistence.MailDAO;
import sanity.nil.mailservice.application.interfaces.persistence.MailReader;
import sanity.nil.mailservice.domain.events.OrderCreatedEvent;
import sanity.nil.mailservice.domain.events.OrderProductCreate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CreatedOrderMailInteractor {

    private final MailContentTemplate<OrderMailDTO> contentTemplate;
    private final WebTemplate<List<ProductImageDTO>, UUID> webTemplate;
    private final MailSender mailSender;
    private final MailDAO mailDAO;

    public void handle(OrderCreatedEvent event) {
        OrderMailDTO orderMailDTO = new OrderMailDTO();
        orderMailDTO.id = event.getId();
        String[] address = event.getAddress().split(",");
        orderMailDTO.country = address[1];
        orderMailDTO.city = address[2];
        orderMailDTO.street = address[3];
        orderMailDTO.buildingNumber = Integer.parseInt(address[4]);
        orderMailDTO.postalCode = address[5];
        orderMailDTO.totalPrice = event.getTotalPrice();
        List<OrderProductMailDTO> productList = new ArrayList<>();
        for (OrderProductCreate product : event.getProducts()) {
            ProductImageDTO productImage = webTemplate.get(product.getProductID()).get(0);
            OrderProductMailDTO productMailDTO = new OrderProductMailDTO();
            productMailDTO.link = "http://localhost:8080/api/v1/product/" + product.getProductID();
            productMailDTO.imageURL = productImage.imageURL;
            productMailDTO.name = product.getName();
            productMailDTO.price = product.getPrice();
            productMailDTO.quantity = product.getQuantity();
            productList.add(productMailDTO);
        }
        orderMailDTO.products = productList;
        String mailContent = contentTemplate.load(orderMailDTO);
        MailDTO mailDTO = mailSender.sendSimpleEmail(new String[]{"dbaturin02@gmail.com"}, MailType.CREATED_ORDER, mailContent);
        MailDTO savedMail = mailDAO.save(mailDTO);
    }

}
