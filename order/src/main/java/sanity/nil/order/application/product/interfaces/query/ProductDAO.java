package sanity.nil.order.application.product.interfaces.query;

import sanity.nil.order.domain.product.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductDAO {

   Product createProduct(Product product, List<String> imageNames);

   Product updateProduct(Product product, List<String> imageNames);

   void deleteByProductId(UUID id);

}
