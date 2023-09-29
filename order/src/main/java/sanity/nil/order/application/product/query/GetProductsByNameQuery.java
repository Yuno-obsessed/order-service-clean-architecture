package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;

@RequiredArgsConstructor
public class GetProductsByNameQuery {

    private final ProductReader productReader;

    public ProductQueryDTO handle(String name) {
        return productReader.getProductQueryByName(name);
    }
}
