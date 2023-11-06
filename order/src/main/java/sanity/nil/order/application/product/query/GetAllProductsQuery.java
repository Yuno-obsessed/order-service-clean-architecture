package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.application.dto.BaseFilters;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsQuery {

    private final ProductReader productReader;

    public List<ProductQueryDTO> handle(ProductQueryFilters productFilters) {
        return productReader.getProductQueriesWithFilters(productFilters);
    }

    public List<ProductQueryDTO> handle(BaseFilters filters) {
        return productReader.getProductsQueriesWithPagination(filters);
    }
}
