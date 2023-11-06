package sanity.nil.order.application.product.interfaces.persistence;

import sanity.nil.order.domain.product.entity.Product;

import java.util.List;

public interface ProductDAO {

   Product createProduct(Product product, List<String> imageNames);

   Product updateProduct(Product product, List<String> imageNames);

}
