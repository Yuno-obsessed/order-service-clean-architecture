package sanity.nil.onlineshop.application.product.interfaces.query;


import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.domain.product.entity.Product;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ProductDAO {

   Product createProduct(Product product);

   Product updateProduct(Product product);

   void deleteByProductId(UUID id);

}
