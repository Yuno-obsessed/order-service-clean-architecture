package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.dto.BaseFilters;
import sanity.nil.order.application.common.interfaces.storage.FileStorage;
import sanity.nil.order.application.product.dto.query.ProductCardQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsQuery {

    private final ProductReader productReader;
    private final FileStorage storage;

    public List<ProductCardQueryDTO> handle(ProductQueryFilters productFilters) {
        if (productFilters.orderBy == null || productFilters.orderBy.isEmpty()) {
            productFilters.orderBy = "created_at";
        }
        List<ProductCardQueryDTO> productCardQueries = productReader.getProductQueriesWithFilters(productFilters);
        for (ProductCardQueryDTO productCardQuery : productCardQueries) {
            if (productCardQuery.imageURL != null && !productCardQuery.imageURL.isEmpty()) {
                String[] nameAndBucket = productCardQuery.imageURL.split(",");
                productCardQuery.imageURL = storage.getFileURL(nameAndBucket[0], nameAndBucket[1]);
            }
            if (productCardQuery.discount != null) {
                productCardQuery.actualPrice = productCardQuery.price
                        .subtract(productCardQuery.price
                                .divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP)
                                .multiply(BigDecimal.valueOf(productCardQuery.discount)));
            }
        }
        return productCardQueries;
    }

    public List<ProductQueryDTO> handle(BaseFilters filters) {
        return productReader.getProductsQueriesWithPagination(filters);
    }
}
