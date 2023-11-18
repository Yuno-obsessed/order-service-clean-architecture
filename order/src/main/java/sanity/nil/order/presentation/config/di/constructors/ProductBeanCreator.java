package sanity.nil.order.presentation.config.di.constructors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import sanity.nil.order.application.common.interfaces.storage.FileStorage;
import sanity.nil.order.application.product.command.*;
import sanity.nil.order.application.product.interfaces.persistence.*;
import sanity.nil.order.application.product.query.GetAllImagesQuery;
import sanity.nil.order.application.product.query.GetAllProductsQuery;
import sanity.nil.order.application.product.query.GetProductByIdQuery;
import sanity.nil.order.application.product.query.GetProductsByNameQuery;
import sanity.nil.order.application.product.service.ProductCommandService;
import sanity.nil.order.application.product.service.ProductQueryService;
import sanity.nil.order.domain.product.service.ProductService;
import sanity.nil.order.infrastructure.database.impl.ProductDAOImpl;
import sanity.nil.order.infrastructure.database.orm.DiscountORM;
import sanity.nil.order.infrastructure.database.orm.ProductImageORM;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.ProductSubtypeORM;
import sanity.nil.order.infrastructure.messageBroker.config.RabbitConfig;
import sanity.nil.order.infrastructure.storage.config.MinioConfig;
import sanity.nil.order.infrastructure.storage.directories.ProductStorage;
import sanity.nil.order.presentation.consumer.subscribers.ProductSubscribers;

@Configuration
@ComponentScans(value = {
        @ComponentScan("sanity.nil.order.infrastructure"),
        @ComponentScan("sanity.nil.order.domain.product"),
        @ComponentScan("sanity.nil.order.application.product"),
        @ComponentScan("sanity.nil.order.presentation")
})
public class ProductBeanCreator {

    @Bean
    public ProductDAO productDAO(ProductORM productORM, ProductSubtypeORM productSubtypeORM,
                                 DiscountORM discountORM, ProductImageORM productImageORM) {
        return new ProductDAOImpl(productORM, productSubtypeORM, discountORM, productImageORM);
    }

    @Bean
    public ProductReader productReader(ProductORM productORM, ProductSubtypeORM productSubtypeORM,
                                       DiscountORM discountORM, ProductImageORM productImageORM) {
        return new ProductDAOImpl(productORM, productSubtypeORM, discountORM, productImageORM);
    }

    @Bean
    public ProductSubtypeReader productSubtypeReader(ProductORM productORM, ProductSubtypeORM productSubtypeORM,
                                                     DiscountORM discountORM, ProductImageORM productImageORM) {
        return new ProductDAOImpl(productORM, productSubtypeORM, discountORM, productImageORM);
    }

    @Bean
    public DiscountReader discountReader(ProductORM productORM, ProductSubtypeORM productSubtypeORM,
                                         DiscountORM discountORM, ProductImageORM productImageORM) {
        return new ProductDAOImpl(productORM, productSubtypeORM, discountORM, productImageORM);
    }

    @Bean
    public ProductImageReader productImageReader(ProductORM productORM, ProductSubtypeORM productSubtypeORM,
                                                 DiscountORM discountORM, ProductImageORM productImageORM) {
        return new ProductDAOImpl(productORM, productSubtypeORM, discountORM, productImageORM);
    }

    @Bean
    public Queue productQueue(RabbitConfig rabbitConfig) {
        return new Queue(rabbitConfig.getProductQueue(), true, false, false);
    }

    @Bean
    public Binding productAddedBinding(@Qualifier("productQueue") Queue queue,
                                       @Qualifier("topicExchange") TopicExchange topicExchange,
                                       RabbitConfig rabbitConfig) {
        return BindingBuilder.bind(queue).to(topicExchange).with(rabbitConfig.getOrderAddedProductRK());
    }

    @Bean
    public ProductSubscribers productSubscribers(ProductDAO productDAO,
                                                 @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        return new ProductSubscribers(productDAO, objectMapper);
    }

    @Bean
    public FileStorage productFileStorage(MinioConfig minioConfig) {
        return new ProductStorage(minioConfig);
    }

    @Bean
    public ProductCommandService productCommandService(ProductDAO productDAO, ProductReader productReader,
                                                       ProductSubtypeReader productSubtypeReader,
                                                       DiscountReader discountReader,
                                                       @Qualifier("productFileStorage") FileStorage fileStorage) {
        ProductService service = new ProductService();
        return new ProductCommandService(
                new CreateProductCommand(productDAO, productSubtypeReader, discountReader, service),
                new UpdateProductCommand(productDAO, productReader, productSubtypeReader, discountReader, service),
                new UpdateProductStatisticsCommand(productDAO, productReader, service),
                new DeleteProductCommand(productDAO, productReader, service),
                new AddImagesCommand(productDAO, productReader, service, fileStorage)
        );
    }

    @Bean
    public ProductQueryService productQueryService(ProductReader productReader, ProductImageReader productImageReader,
                                                   @Qualifier("productFileStorage") FileStorage fileStorage) {
        return new ProductQueryService(
                new GetAllProductsQuery(productReader),
                new GetProductByIdQuery(productReader),
                new GetProductsByNameQuery(productReader),
                new GetAllImagesQuery(productImageReader, fileStorage)
        );
    }

}
