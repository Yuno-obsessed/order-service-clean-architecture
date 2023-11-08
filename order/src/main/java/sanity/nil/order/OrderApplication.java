package sanity.nil.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import sanity.nil.order.application.common.presentation.config.RelayBeanCreator;
import sanity.nil.order.presentation.config.di.constructors.OrderBeanCreator;
import sanity.nil.order.presentation.config.di.constructors.ProductBeanCreator;

@SpringBootApplication
@Import({ProductBeanCreator.class, OrderBeanCreator.class, RelayBeanCreator.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
