package sanity.nil.tourservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import sanity.nil.tourservice.presentation.config.di.country.CountryBeanCreator;

@SpringBootApplication
@Import(CountryBeanCreator.class)
public class SpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

}
