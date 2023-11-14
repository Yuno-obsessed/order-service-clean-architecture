package sanity.nil.order.application.product.interfaces.persistence;

import sanity.nil.order.domain.product.entity.Product;

import java.util.UUID;

public interface ProductDAO {

   Product createProduct(Product product);

   Product updateProduct(Product product);

   void increaseQuantity(UUID id, int quantity);

   void decreaseQuantity(UUID id, int quantity);

}
