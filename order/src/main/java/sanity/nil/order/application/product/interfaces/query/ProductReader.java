package sanity.nil.order.application.product.interfaces.query;


import sanity.nil.order.application.product.dto.GetProductDTO;
import sanity.nil.order.application.product.dto.ProductDTO;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.product.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductReader {

    List<OrderProduct> getProductsByIds(List<UUID> ids);

    GetProductDTO getGETProductDTOById(UUID id);

    ProductDTO getProductDTOById(UUID id);

    Product getProductById(UUID id);
}
