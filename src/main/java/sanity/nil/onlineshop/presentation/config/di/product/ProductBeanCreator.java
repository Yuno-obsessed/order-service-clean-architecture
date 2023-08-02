package sanity.nil.onlineshop.presentation.config.di.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sanity.nil.onlineshop.application.product.interactors.CreateProductInteractorImpl;
import sanity.nil.onlineshop.application.product.interactors.DeleteProductInteractorImpl;
import sanity.nil.onlineshop.application.product.interactors.GetProductInteractorImpl;
import sanity.nil.onlineshop.application.product.interactors.UpdateProductInteractorImpl;
import sanity.nil.onlineshop.application.product.interfaces.ProductRepository;
import sanity.nil.onlineshop.application.product.interfaces.interactors.CreateProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.interactors.DeleteProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.interactors.GetProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.interactors.UpdateProductInteractor;
import sanity.nil.onlineshop.domain.product.methods.CreateProduct;
import sanity.nil.onlineshop.domain.product.methods.UpdateProduct;
import sanity.nil.onlineshop.domain.product.methods.impl.CreateProductImpl;
import sanity.nil.onlineshop.domain.product.methods.impl.UpdateProductImpl;
import sanity.nil.onlineshop.infrastructure.dao.ProductDAO;
import sanity.nil.onlineshop.infrastructure.impl.ProductRepositoryImpl;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.onlineshop.infrastructure"),
        @ComponentScan("sanity.nil.onlineshop.domain"),
        @ComponentScan("sanity.nil.onlineshop.application"),
        @ComponentScan("sanity.nil.onlineshop.presentation")
})
@EnableJpaRepositories("sanity.nil.onlineshop.infrastructure.dao")
public class ProductBeanCreator {

    @Bean
    public ProductRepository productRepository(ProductDAO productDAO) {
        return new ProductRepositoryImpl(productDAO);
    }

    @Bean
    public CreateProduct createProduct() {
       return new CreateProductImpl();
    }

    @Bean
    public UpdateProduct updateProduct() {
       return new UpdateProductImpl();
    }

    @Bean
    public CreateProductInteractor createProductInteractor(ProductRepository productRepository, CreateProduct createProduct) {
      return new CreateProductInteractorImpl(productRepository, createProduct);
    }

    @Bean
    public UpdateProductInteractor updateProductInteractor(ProductRepository productRepository, UpdateProduct updateProduct) {
       return new UpdateProductInteractorImpl(productRepository, updateProduct);
    }

    @Bean
    public GetProductInteractor getProductInteractor(ProductRepository productRepository) {
      return new GetProductInteractorImpl(productRepository);
    }

    @Bean
    public DeleteProductInteractor deleteProductInteractor(ProductRepository productRepository) {
        return new DeleteProductInteractorImpl(productRepository);
    }

}
