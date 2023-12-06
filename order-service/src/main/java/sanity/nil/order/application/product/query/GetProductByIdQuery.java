package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductByIdQuery {

    private final ProductReader productReader;

    public ProductQueryDTO handle(UUID id) {
        return productReader.getProductQueryById(id);
    }
}
