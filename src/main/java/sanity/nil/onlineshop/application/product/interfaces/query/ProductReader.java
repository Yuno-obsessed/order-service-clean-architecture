package sanity.nil.onlineshop.application.product.interfaces.query;

import sanity.nil.onlineshop.application.product.dto.GetProductDTO;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.domain.product.entity.Product;

import java.util.UUID;

public interface ProductReader {

    GetProductDTO getGETProductDTOById(UUID id);
    ProductDTO getProductDTOById(UUID id);

    Product getProductById(UUID id);
}
