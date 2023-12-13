package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.dto.BaseFilters;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsQuery {

    private final ProductReader productReader;

    public List<ProductQueryDTO> handle(ProductQueryFilters productFilters) {
        if (productFilters.orderBy == null || productFilters.orderBy.isEmpty()) {
            productFilters.orderBy = "created_at";
        }
        return productReader.getProductQueriesWithFilters(productFilters);
    }

    public List<ProductQueryDTO> handle(BaseFilters filters) {
        return productReader.getProductsQueriesWithPagination(filters);
    }
}
