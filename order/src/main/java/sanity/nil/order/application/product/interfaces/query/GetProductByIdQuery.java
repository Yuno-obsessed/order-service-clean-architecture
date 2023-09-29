package sanity.nil.order.application.product.interfaces.query;

import sanity.nil.order.application.product.dto.query.ProductQueryDTO;

import java.util.UUID;

public interface GetProductByIdQuery {

    ProductQueryDTO get(UUID id);
}
