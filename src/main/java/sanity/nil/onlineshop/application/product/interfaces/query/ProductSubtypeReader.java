package sanity.nil.onlineshop.application.product.interfaces.query;

import sanity.nil.onlineshop.domain.product.vo.ProductSubtype;

public interface ProductSubtypeReader {

    ProductSubtype getBySubtypeId(Integer id);
}
