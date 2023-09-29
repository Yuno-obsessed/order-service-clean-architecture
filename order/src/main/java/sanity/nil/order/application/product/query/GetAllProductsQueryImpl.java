package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.dto.BaseFilters;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.application.product.interfaces.query.GetAllProductsQuery;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsQueryImpl implements GetAllProductsQuery {

    private final ProductReader productReader;

    @Override
    public List<ProductQueryDTO> getAllProductsWithProductFilters(ProductQueryFilters productFilters) {
        return productReader.getProductQueriesWithFilters(productFilters);
    }

    @Override
    public List<ProductQueryDTO> getAllProductsWithPagination(BaseFilters filters) {
        return productReader.getProductsQueriesWithPagination(filters);
    }
}
