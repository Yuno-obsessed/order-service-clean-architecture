package sanity.nil.onlineshop.presentation.config.di.constructors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sanity.nil.onlineshop.application.product.interactors.*;
import sanity.nil.onlineshop.application.product.interfaces.interactors.*;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductDAO;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductReader;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductSubtypeReader;
import sanity.nil.onlineshop.domain.product.service.ProductService;
import sanity.nil.onlineshop.infrastructure.database.impl.ProductSubtypeDAOImpl;
import sanity.nil.onlineshop.infrastructure.database.orm.ProductORM;
import sanity.nil.onlineshop.infrastructure.database.impl.ProductDAOImpl;
import sanity.nil.onlineshop.infrastructure.database.orm.ProductSubtypeORM;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.onlineshop.infrastructure"),
        @ComponentScan("sanity.nil.onlineshop.domain"),
        @ComponentScan("sanity.nil.onlineshop.application"),
        @ComponentScan("sanity.nil.onlineshop.presentation")
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
