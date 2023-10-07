package sanity.nil.onlineshop.product.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sanity.nil.product.application.interfaces.persistence.ProductDAO;
import sanity.nil.product.domain.entity.Product;
import sanity.nil.product.domain.service.ProductService;
import sanity.nil.onlineshop.product.infrastructure.ProductInMemoryDAOImpl;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sanity.nil.onlineshop.product.util.EntityGenerator.generateActiveDiscount;
import static sanity.nil.onlineshop.product.util.EntityGenerator.generateProduct;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateProductTest {

    private static ProductDAO productDAO;
    private static ProductService productService;
    private static Product createdProduct;

    @BeforeAll
    public static void initTest(){
        productDAO = new ProductInMemoryDAOImpl();
        productService = new ProductService();

        Product product = generateProduct(UUID.randomUUID(), "test_name", 25.5, generateActiveDiscount(30));
        createdProduct = productDAO.createProduct(product, null);
    }

    @Test
    public void successUpdateProduct() {
       Product product = productService.update(createdProduct.getProductId().getId(), createdProduct.getDescription(), "test_name_2",
               createdProduct.getPrice(), createdProduct.getDiscount().getDiscountType().getCode(), createdProduct.getDiscount().getStartsAt(),
               createdProduct.getDiscount().getEndsAt(), createdProduct.getQuantity(), createdProduct.getProductSubtype(),
               createdProduct.getProductStatistics());

       Product updatedProduct = productDAO.updateProduct(product, null);
       assertThat(createdProduct).isNotEqualTo(updatedProduct);
    }
}
