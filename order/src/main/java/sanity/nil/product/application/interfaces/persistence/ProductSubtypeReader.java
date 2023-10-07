package sanity.nil.product.application.interfaces.persistence;


import sanity.nil.product.domain.vo.ProductSubtype;

public interface ProductSubtypeReader {

    ProductSubtype getBySubtypeId(Integer id);
}
