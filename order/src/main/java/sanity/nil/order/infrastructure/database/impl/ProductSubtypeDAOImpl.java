package sanity.nil.order.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.exceptions.SubtypeNotFound;
import sanity.nil.order.application.product.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.order.domain.product.vo.ProductSubtype;
import sanity.nil.order.infrastructure.database.models.ProductSubtypeModel;
import sanity.nil.order.infrastructure.database.orm.ProductSubtypeORM;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductSubtypeMapper;

@RequiredArgsConstructor
public class ProductSubtypeDAOImpl implements ProductSubtypeReader {

    private final ProductSubtypeORM productSubtypeORM;

    @Override
    public ProductSubtype getBySubtypeId(Integer id) {
        ProductSubtypeModel maybeProductSubtype = productSubtypeORM.findById(id).orElseThrow(() ->
                SubtypeNotFound.throwEx(id));
        return ProductSubtypeMapper.convertModelToEntity(maybeProductSubtype);
    }
}
