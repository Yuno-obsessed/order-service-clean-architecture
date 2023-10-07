package sanity.nil.product.application.interfaces.persistence;


import sanity.nil.common.application.dto.BaseFilters;
import sanity.nil.product.application.dto.query.ProductQueryDTO;
import sanity.nil.product.application.dto.query.ProductQueryFilters;
import sanity.nil.product.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductReader {

    Product getProductById(UUID id);

    List<ProductQueryDTO> getProductsQueriesWithPagination(BaseFilters filters);

    ProductQueryDTO getProductQueryById(UUID id);

    ProductQueryDTO getProductQueryByName(String name);

    List<ProductQueryDTO> getProductQueriesWithFilters(ProductQueryFilters filters);
}
