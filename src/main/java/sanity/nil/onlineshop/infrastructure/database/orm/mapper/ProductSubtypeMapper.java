package sanity.nil.onlineshop.infrastructure.database.orm.mapper;

import sanity.nil.onlineshop.application.product.dto.types.ProductSubtypeDTO;
import sanity.nil.onlineshop.application.product.dto.types.ProductTypeDTO;
import sanity.nil.onlineshop.domain.product.vo.ProductSubtype;
import sanity.nil.onlineshop.domain.product.vo.ProductType;
import sanity.nil.onlineshop.infrastructure.database.model.ProductSubtypeModel;
import sanity.nil.onlineshop.infrastructure.database.model.ProductTypeModel;

public class ProductSubtypeMapper {

    public static ProductSubtypeDTO convertModelToProductSubtypeDTO(ProductSubtypeModel model) {
        return new ProductSubtypeDTO(model.getProductSubtypeId(), model.getSubtypeName(), model.getSubtypePrefix());
    }
    public static ProductTypeDTO convertModelToProductTypeDTO(ProductTypeModel model) {
        return new ProductTypeDTO(model.getTypeName(), model.getPrefix(),
                convertModelToProductSubtypeDTO(model.getSubtypes().get(0)));
    }

    public static ProductTypeDTO convertEntityToProductTypeDTO(ProductSubtype entity) {
        return new ProductTypeDTO(entity.getProductType().getProductTypeName(), entity.getProductType().getPrefix(),
                new ProductSubtypeDTO(entity.getSubtypeId(), entity.getSubtypeName(), entity.getSubtypePrefix()));
    }

    public static ProductSubtypeModel convertEntityToModel(ProductSubtype productSubtype) {
        return new ProductSubtypeModel(productSubtype.getSubtypeId(), productSubtype.getSubtypeName(), productSubtype.getSubtypePrefix(),
                convertProductTypeEntityToModel(productSubtype.getProductType()));
    }

   public static ProductSubtype convertModelToEntity(ProductSubtypeModel productSubtypeModel)  {
        return new ProductSubtype(productSubtypeModel.getProductSubtypeId(), productSubtypeModel.getSubtypeName(),
                productSubtypeModel.getSubtypePrefix(), convertProductTypeModelToEntity(productSubtypeModel.getProductType()));
   }

    public static ProductType convertProductTypeModelToEntity(ProductTypeModel productTypeModel) {
        return new ProductType(productTypeModel.getTypeName(), productTypeModel.getPrefix());
    }

    public static ProductTypeModel convertProductTypeEntityToModel(ProductType productType) {
        return new ProductTypeModel(productType.getProductTypeName(), productType.getPrefix());
    }
}
