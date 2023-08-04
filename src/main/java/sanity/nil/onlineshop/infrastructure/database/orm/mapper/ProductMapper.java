package sanity.nil.onlineshop.infrastructure.database.orm.mapper;

import sanity.nil.onlineshop.application.product.dto.DiscountDTO;
import sanity.nil.onlineshop.application.product.dto.GetProductDTO;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.entity.consts.ProductType;
import sanity.nil.onlineshop.domain.product.vo.Discount;
import sanity.nil.onlineshop.domain.product.vo.ProductID;
import sanity.nil.onlineshop.domain.product.vo.State;
import sanity.nil.onlineshop.infrastructure.database.model.ProductModel;

import static sanity.nil.onlineshop.domain.product.vo.Discount.DiscountType.getByDiscount;
import static sanity.nil.onlineshop.infrastructure.database.orm.mapper.ProductTypeMapper.*;

public class ProductMapper {

    public static ProductModel convertEntityToModel(Product product) {
        ProductModel model = new ProductModel();
        model.setProductId(product.getProductId().getId());
        model.setDescription(product.getDescription());
        model.setName(product.getName());
        model.setPrice(product.getPrice());
        if (product.getDiscount() != null) {
            model.setDiscount(product.getDiscount().getDiscountType().getDiscount());
            model.setDiscountStart(product.getDiscount().getStartsAt());
            model.setDiscountEnd(product.getDiscount().getEndsAt());
        }
        model.setPriceWithDiscount(product.getActualPrice());
        model.setQuantity(product.getQuantity());
        model.setAvailability(product.isAvailable());
        if (product.getState() != null) {
            model.setDeleted(product.getState().isDeleted());
            model.setDeletedAt(product.getState().getDeletedAt());
        }
        model.setProductType(ProductTypeMapper.convertEntityToModel(product.getProductType()));
        return model;
    }

    public static Product convertModelToEntity(ProductModel model) {
        return new Product(new ProductID(model.getProductId()),
                model.getDescription(), model.getName(), model.getPrice(),
                new Discount(getByDiscount(model.getDiscount()),
                        model.getDiscountStart(), model.getDiscountEnd()),
                model.getPriceWithDiscount(), model.getQuantity(), model.isAvailability(),
                new State(model.isDeleted(), model.getDeletedAt()),
                ProductType.getByTypeId(model.getProductType().getTypeId()));
    }

    public static ProductDTO convertModelToProductDTO(ProductModel model) {
        return new ProductDTO(model.getProductId(), model.getDescription(),
                model.getName(), model.getPrice(), new DiscountDTO(model.getDiscount(),
                    model.getDiscountStart(), model.getDiscountEnd(), model.isDiscountExpired()),
                model.getPriceWithDiscount(), convertModelToProductTypeDTO(model.getProductType()),
                model.getQuantity(), model.isAvailability()
        );
    }

    public static ProductDTO convertEntityToProductDTO(Product entity) {
        DiscountDTO discountDTO = null;
        if (entity.getDiscount() != null) {
            discountDTO = new DiscountDTO(entity.getDiscount().getDiscountType().getDiscount(),
                    entity.getDiscount().startsAt, entity.getDiscount().endsAt, entity.getDiscount().isExpired());
        }
        return new ProductDTO(entity.getProductId().getId(), entity.getDescription(),
                entity.getName(), entity.getPrice(), discountDTO,
                entity.getActualPrice(), convertEntityToProductTypeDTO(entity.getProductType()),
                entity.getQuantity(), entity.isAvailable()
        );
    }

    public static GetProductDTO convertModelToGetProductDTO(ProductModel model) {
        return new GetProductDTO(model.getProductId(), model.getDescription(),
                model.getName(), model.getPrice(), new DiscountDTO(model.getDiscount(),
                model.getDiscountStart(), model.getDiscountEnd(), model.isDiscountExpired()),
                model.getPriceWithDiscount(), model.getQuantity(), model.isAvailability(),
                model.getCreatedAt(), model.getUpdatedAt()
        );
    }
}
