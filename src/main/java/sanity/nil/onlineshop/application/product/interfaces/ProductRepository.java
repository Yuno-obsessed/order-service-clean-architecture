package sanity.nil.onlineshop.application.product.interfaces;


import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.domain.product.entity.Product;

import java.util.UUID;

public interface ProductRepository {

   ProductDTO createProduct(Product product);

   ProductDTO getByProductId(UUID id);

   ProductDTO updateProduct(Product product);

   void deleteByProductId(UUID id);

}
