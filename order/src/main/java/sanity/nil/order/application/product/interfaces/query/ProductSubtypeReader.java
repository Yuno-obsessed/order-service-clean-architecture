package sanity.nil.order.application.product.interfaces.query;


import sanity.nil.order.domain.product.vo.ProductSubtype;

public interface ProductSubtypeReader {

    ProductSubtype getBySubtypeId(Integer id);
}
