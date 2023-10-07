package sanity.nil.product.application.interfaces.persistence;

import sanity.nil.product.domain.entity.Product;

import java.util.List;

public interface ProductDAO {

   Product createProduct(Product product, List<String> imageNames);

   Product updateProduct(Product product, List<String> imageNames);

}
