package sanity.nil.mailservice.infrastructure.mail.template;

import lombok.RequiredArgsConstructor;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import sanity.nil.mailservice.application.dto.mail.OrderMailDTO;
import sanity.nil.mailservice.application.interfaces.MailContentTemplate;

@RequiredArgsConstructor
public class OrderCreatedMailTemplate implements MailContentTemplate<OrderMailDTO> {

    private final SpringTemplateEngine templateEngine;

    @Override
    public String load(OrderMailDTO orderMailDTO) {
        Context context = new Context();
        context.setVariable("order", orderMailDTO);
        context.setVariable("delivery_status_url", "#");
        return templateEngine.process("created_order.html", context);
    }
}
