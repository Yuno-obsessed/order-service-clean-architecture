package sanity.nil.order.presentation.config.di.constructors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import sanity.nil.order.application.product.command.CreateProductCommandImpl;
import sanity.nil.order.application.product.command.DeleteProductCommandImpl;
import sanity.nil.order.application.product.command.UpdateProductCommandImpl;
import sanity.nil.order.application.product.command.UpdateProductStatisticsCommandImpl;
import sanity.nil.order.application.product.interfaces.command.CreateProductCommand;
import sanity.nil.order.application.product.interfaces.command.DeleteProductCommand;
import sanity.nil.order.application.product.interfaces.command.UpdateProductCommand;
import sanity.nil.order.application.product.interfaces.command.UpdateProductStatisticsCommand;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.application.product.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.order.application.product.interfaces.query.GetAllProductsQuery;
import sanity.nil.order.application.product.interfaces.query.GetProductByIdQuery;
import sanity.nil.order.application.product.interfaces.query.GetProductByNameQuery;
import sanity.nil.order.application.product.query.GetAllProductsQueryImpl;
import sanity.nil.order.application.product.query.GetProductByIdQueryImpl;
import sanity.nil.order.application.product.query.GetProductsByNameQueryImpl;
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
    public CreateProductCommand createProductCommand(ProductDAO productDAO, ProductSubtypeReader productSubtypeReader) {
      return new CreateProductCommandImpl(productDAO, productSubtypeReader, new ProductService());
    }

    @Bean
    public UpdateProductCommand updateProductCommand(ProductDAO productDAO, ProductReader productReader,
                                                        ProductSubtypeReader productSubtypeReader) {
        return new UpdateProductCommandImpl(productDAO, productReader, productSubtypeReader, new ProductService());
    }

    @Bean
    public GetProductByIdQuery getProductByIdQuery(ProductReader productReader) {
        return new GetProductByIdQueryImpl(productReader);
    }

    @Bean
    public GetProductByNameQuery getProductByNameQuery(ProductReader productReader) {
        return new GetProductsByNameQueryImpl(productReader);
    }

    @Bean
    public GetAllProductsQuery getAllProductsQuery(ProductReader productReader) {
        return new GetAllProductsQueryImpl(productReader);
    }

    @Bean
    public DeleteProductCommand deleteProductInteractor(ProductDAO productDAO, ProductReader productReader) {
        return new DeleteProductCommandImpl(productDAO, productReader, new ProductService());
    }

    @Bean
    public UpdateProductStatisticsCommand updateProductStatisticsCommand(ProductDAO productDAO, ProductReader productReader) {
        return new UpdateProductStatisticsCommandImpl(productDAO, productReader, new ProductService());
    }

}
