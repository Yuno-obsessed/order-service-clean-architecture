package sanity.nil.product.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.product.application.dto.query.ProductQueryDTO;
import sanity.nil.product.application.interfaces.persistence.ProductReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductByIdQuery {

    private final ProductReader productReader;

    public ProductQueryDTO handle(UUID id) {
        return productReader.getProductQueryById(id);
    }
}
