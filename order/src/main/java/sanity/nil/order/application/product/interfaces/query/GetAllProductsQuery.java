package sanity.nil.order.application.product.interfaces.query;

import sanity.nil.order.application.common.dto.BaseFilters;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;

import java.util.List;

public interface GetAllProductsQuery {

    List<ProductQueryDTO> getAllProductsWithProductFilters(ProductQueryFilters productFilters);

    List<ProductQueryDTO> getAllProductsWithPagination(BaseFilters filters);
}
