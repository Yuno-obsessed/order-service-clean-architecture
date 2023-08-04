package sanity.nil.onlineshop.infrastructure.database.orm.mapper;

import sanity.nil.onlineshop.application.product.dto.ProductTypeDTO;
import sanity.nil.onlineshop.domain.product.entity.consts.ProductType;
import sanity.nil.onlineshop.infrastructure.database.model.ProductTypeModel;

public class ProductTypeMapper {
    public static ProductTypeDTO convertModelToProductTypeDTO(ProductTypeModel model) {
        return new ProductTypeDTO(model.getTypeId(), model.getType(), model.getPrefix());
    }

    public static ProductTypeDTO convertEntityToProductTypeDTO(ProductType entity) {
        return new ProductTypeDTO(entity.getTypeId(), entity.getType(), entity.getPrefix());
    }

    public static ProductTypeModel convertEntityToModel(ProductType productType) {
        return new ProductTypeModel(productType.getTypeId(), productType.getType(), productType.getPrefix());
    }
}
