package sanity.nil.order.presentation.config.di.constructors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import sanity.nil.order.application.product.interactors.*;
import sanity.nil.order.application.product.interfaces.interactors.*;
import sanity.nil.order.application.product.interfaces.query.ProductDAO;
import sanity.nil.order.application.product.interfaces.query.ProductReader;
import sanity.nil.order.application.product.interfaces.query.ProductSubtypeReader;
import sanity.nil.order.domain.product.service.ProductService;
import sanity.nil.order.infrastructure.database.impl.ProductDAOImpl;
import sanity.nil.order.infrastructure.database.impl.ProductSubtypeDAOImpl;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.ProductSubtypeORM;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.order.infrastructure"),
        @ComponentScan("sanity.nil.order.domain"),
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
    public CreateProductInteractor createProductInteractor(ProductDAO productDAO, ProductReader productReader,
                                                           ProductSubtypeReader productSubtypeReader) {
      return new CreateProductInteractorImpl(productDAO, productReader, productSubtypeReader, new ProductService());
    }

    @Bean
    public UpdateProductInteractor updateProductInteractor(ProductDAO productDAO, ProductReader productReader,
                                                           ProductSubtypeReader productSubtypeReader) {
        return new UpdateProductInteractorImpl(productDAO, productReader, productSubtypeReader, new ProductService());
    }

    @Bean
    public GetProductInteractor getProductInteractor(ProductReader productReader) {
        return new GetProductInteractorImpl(productReader);
    }

    @Bean
    public DeleteProductInteractor deleteProductInteractor(ProductDAO productDAO, ProductReader productReader) {
        return new DeleteProductInteractorImpl(productDAO, productReader, new ProductService());
    }

    @Bean
    public UpdateProductStatisticsInteractor updateProductStatisticsInteractor(ProductDAO productDAO, ProductReader productReader) {
        return new UpdateProductStatisticsInteractorImpl(productDAO, productReader, new ProductService());
    }

}
