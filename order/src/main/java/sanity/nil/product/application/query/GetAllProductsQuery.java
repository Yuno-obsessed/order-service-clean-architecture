package sanity.nil.product.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.common.application.dto.BaseFilters;
import sanity.nil.product.application.dto.query.ProductQueryDTO;
import sanity.nil.product.application.dto.query.ProductQueryFilters;
import sanity.nil.product.application.interfaces.persistence.ProductReader;

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
