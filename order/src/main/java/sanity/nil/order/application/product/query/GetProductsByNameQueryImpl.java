package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.application.product.interfaces.query.GetProductByNameQuery;

@RequiredArgsConstructor
public class GetProductsByNameQueryImpl implements GetProductByNameQuery {

    private final ProductReader productReader;

    @Override
    public ProductQueryDTO get(String name) {
        return productReader.getProductQueryByName(name);
    }
}
