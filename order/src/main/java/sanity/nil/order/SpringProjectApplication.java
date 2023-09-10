package sanity.nil.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import sanity.nil.onlineshop.presentation.config.di.constructors.ProductBeanCreator;

@SpringBootApplication
@Import(ProductBeanCreator.class)
public class SpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

}
