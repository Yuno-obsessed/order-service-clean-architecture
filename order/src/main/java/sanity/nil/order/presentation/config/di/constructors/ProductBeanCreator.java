package sanity.nil.order.presentation.config.di.constructors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import sanity.nil.order.application.product.command.CreateProductCommand;
import sanity.nil.order.application.product.command.DeleteProductCommand;
import sanity.nil.order.application.product.command.UpdateProductCommand;
import sanity.nil.order.application.product.command.UpdateProductStatisticsCommand;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.application.product.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.order.application.product.query.GetAllProductsQuery;
import sanity.nil.order.application.product.query.GetProductByIdQuery;
import sanity.nil.order.application.product.query.GetProductsByNameQuery;
import sanity.nil.order.application.product.service.ProductCommandService;
import sanity.nil.order.application.product.service.ProductQueryService;
import sanity.nil.order.domain.product.service.ProductService;
import sanity.nil.order.infrastructure.database.impl.ProductDAOImpl;
import sanity.nil.order.infrastructure.database.impl.ProductSubtypeDAOImpl;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.ProductSubtypeORM;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.order.infrastructure"),
        @ComponentScan("sanity.nil.order.domain.product"),
        @ComponentScan("sanity.nil.order.application.product"),
        @ComponentScan("sanity.nil.order.presentation")
})
public class ProductBeanCreator {

    @Bean
    public ProductDAO productDAO(ProductORM productORM) {
        return new ProductDAOImpl(productORM);
    }

    @Bean
    public ProductReader productReader(ProductORM productORM) {
        return new ProductDAOImpl(productORM);
    }

    @Bean
    public ProductSubtypeReader productSubtypeReader(ProductSubtypeORM productSubtypeORM) {
        return new ProductSubtypeDAOImpl(productSubtypeORM);
    }

    @Bean
    public ProductCommandService productCommandService(ProductDAO productDAO, ProductReader productReader,
                                                       ProductSubtypeReader productSubtypeReader) {
        ProductService service = new ProductService();
        return new ProductCommandService(
               new CreateProductCommand(productDAO, productSubtypeReader, service),
               new UpdateProductCommand(productDAO, productReader, productSubtypeReader, service),
               new UpdateProductStatisticsCommand(productDAO, productReader, service),
               new DeleteProductCommand(productDAO, productReader, service)
        );
    }

    @Bean
    public ProductQueryService productQueryService(ProductReader productReader) {
        return new ProductQueryService(
                new GetAllProductsQuery(productReader),
                new GetProductByIdQuery(productReader),
                new GetProductsByNameQuery(productReader)
        );
    }

}
