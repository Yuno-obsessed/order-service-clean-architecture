package sanity.nil.order.product.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;
import sanity.nil.order.product.infrastructure.ProductInMemoryDAOImpl;
import sanity.nil.order.util.EntityGenerator;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteProductTest {

    private static ProductDAO productDAO;
    private static ProductService productService;
    private static Product createdProduct;

    @BeforeAll
    public static void initTest(){
        productDAO = new ProductInMemoryDAOImpl();
        productService = new ProductService();

        Product product = EntityGenerator.generateProduct(UUID.randomUUID(), "test_name", 25.5, EntityGenerator.generateActiveDiscount(30));
        createdProduct = productDAO.createProduct(product);
    }

    @Test
    public void successUpdateProduct() {
        Product product = productService.delete(EntityGenerator.generateProduct(createdProduct));

        Product updatedProduct = productDAO.updateProduct(product);
        assertThat(createdProduct).isNotEqualTo(updatedProduct);
    }
}
