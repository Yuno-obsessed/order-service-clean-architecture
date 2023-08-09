package sanity.nil.onlineshop.application.product.interfaces.query;


import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.domain.product.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductDAO {

   Product createProduct(Product product, List<String> imageNames);

   Product updateProduct(Product product, List<String> imageNames);

   void deleteByProductId(UUID id);

}
