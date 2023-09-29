package sanity.nil.order.application.product.interfaces.query;

import sanity.nil.order.application.product.dto.query.ProductQueryDTO;

public interface GetProductByNameQuery {

    ProductQueryDTO get(String name);
}
