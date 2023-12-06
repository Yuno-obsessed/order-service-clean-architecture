package sanity.nil.order.product.integration;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanity.nil.order.OrderApplication;

@SpringBootTest(classes = OrderApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith({
        SpringExtension.class,
        MockitoExtension.class
})
public class ProductIntegrationTests {

    private static String BASE_URL = "http://localhost:8080/api/v1/product";
    private TestRestTemplate restTemplate;

//    @Test
//    public void test() {
//        assertThat(this.restTemplate.getForObject(BASE_URL + "/", String.class)).contains("");
//    }

}
