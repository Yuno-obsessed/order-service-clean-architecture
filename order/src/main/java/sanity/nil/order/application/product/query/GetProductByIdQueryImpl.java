package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.application.product.interfaces.query.GetProductByIdQuery;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductByIdQueryImpl implements GetProductByIdQuery {

    private final ProductReader productReader;

    @Override
    public ProductQueryDTO get(UUID id) {
        return productReader.getProductQueryById(id);
    }
}
