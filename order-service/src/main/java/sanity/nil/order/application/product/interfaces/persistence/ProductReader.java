package sanity.nil.order.application.product.interfaces.persistence;


import sanity.nil.order.application.common.dto.BaseFilters;
import sanity.nil.order.application.product.dto.query.ProductCardQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.domain.product.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductReader {

    Product getProductById(UUID id);

    List<ProductQueryDTO> getProductsQueriesWithPagination(BaseFilters filters);

    ProductQueryDTO getProductQueryById(UUID id);

    List<ProductCardQueryDTO> getProductQueriesWithFilters(ProductQueryFilters filters);
}
