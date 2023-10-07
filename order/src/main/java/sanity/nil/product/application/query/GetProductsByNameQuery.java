package sanity.nil.product.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.product.application.dto.query.ProductQueryDTO;
import sanity.nil.product.application.interfaces.persistence.ProductReader;

@RequiredArgsConstructor
public class GetProductsByNameQuery {

    private final ProductReader productReader;

    public ProductQueryDTO handle(String name) {
        return productReader.getProductQueryByName(name);
    }
}
