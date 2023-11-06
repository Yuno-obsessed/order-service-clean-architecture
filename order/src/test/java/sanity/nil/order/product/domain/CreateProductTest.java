package sanity.nil.order.product.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.application.common.application.exceptions.DiscountNotFoundException;
import sanity.nil.order.domain.product.exceptions.UnsupportedQuantityException;
import sanity.nil.order.domain.product.service.ProductService;
import sanity.nil.order.product.infrastructure.ProductInMemoryDAOImpl;
import sanity.nil.order.util.EntityGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateProductTest {

    private static ProductDAO productDAO;
    private static ProductService productService;

    @BeforeAll
    public static void initTest(){
       productDAO = new ProductInMemoryDAOImpl();
       productService = new ProductService();
    }

    @Test
    public void successCreateProduct() {
        Product product = productService.create("desc_test", "name_test",
                BigDecimal.valueOf(25.5), 0, LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(4), 4, EntityGenerator.generateProductSubtype(1));
        Product createdProduct = productDAO.createProduct(product, null);
        assertThat(product).isEqualTo(createdProduct);
    }

    @Test
    public void failCreateProductWithWrongDiscountCode() {
        assertThatExceptionOfType(DiscountNotFoundException.class).isThrownBy(
                () -> productService.create("desc_test", "name_test",
                        BigDecimal.valueOf(25.5), 4, LocalDateTime.now().minusDays(1),
                        LocalDateTime.now().plusDays(4), 4, EntityGenerator.generateProductSubtype(1))
        );
    }

    @Test
    public void failCreateProductWithUnsupportedQuantity() {
        assertThatExceptionOfType(UnsupportedQuantityException.class).isThrownBy(
                () -> productService.create("desc_test", "name_test",
                        BigDecimal.valueOf(25.5), 1, LocalDateTime.now().minusDays(1),
                        LocalDateTime.now().plusDays(4), -2, EntityGenerator.generateProductSubtype(1))
        );
    }

}
