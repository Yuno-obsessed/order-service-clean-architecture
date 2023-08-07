package sanity.nil.onlineshop.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.exceptions.SubtypeNotFound;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductSubtypeReader;
import sanity.nil.onlineshop.domain.product.vo.ProductSubtype;
import sanity.nil.onlineshop.infrastructure.database.model.ProductSubtypeModel;
import sanity.nil.onlineshop.infrastructure.database.orm.ProductSubtypeORM;
import sanity.nil.onlineshop.infrastructure.database.orm.mapper.ProductSubtypeMapper;

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
