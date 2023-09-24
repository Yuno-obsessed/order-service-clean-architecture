package sanity.nil.order.application.product.interfaces.persistence;


import sanity.nil.order.domain.product.vo.ProductSubtype;

public interface ProductSubtypeReader {

    ProductSubtype getBySubtypeId(Integer id);
}
