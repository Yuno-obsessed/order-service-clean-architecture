package sanity.nil.onlineshop.infrastructure.database.orm.mapper;

import sanity.nil.onlineshop.application.product.dto.discount.DiscountDTO;
import sanity.nil.onlineshop.application.product.dto.GetProductDTO;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.dto.image.ProductImageDTO;
import sanity.nil.onlineshop.application.product.dto.statistics.ProductStatisticsDTO;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.vo.Discount;
import sanity.nil.onlineshop.domain.product.vo.ProductID;
import sanity.nil.onlineshop.domain.product.vo.ProductStatistics;
import sanity.nil.onlineshop.domain.product.vo.State;
import sanity.nil.onlineshop.infrastructure.database.model.ProductImageModel;
import sanity.nil.onlineshop.infrastructure.database.model.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static sanity.nil.onlineshop.domain.product.vo.Discount.DiscountType.getByDiscount;

public class ProductMapper {

    public static ProductModel convertEntityToModel(Product entity, List<ProductImageModel> images) {
        ProductModel model = new ProductModel();
        model.setProductId(entity.getProductId().getId());
        model.setDescription(entity.getDescription());
        model.setName(entity.getName());
        model.setPrice(entity.getPrice());
        if (entity.getDiscount() != null) {
            model.setDiscount(entity.getDiscount().getDiscountType().getDiscount());
            model.setDiscountStart(entity.getDiscount().getStartsAt());
            model.setDiscountEnd(entity.getDiscount().getEndsAt());
        }
        model.setPriceWithDiscount(entity.getActualPrice());
        model.setQuantity(entity.getQuantity());
        model.setAvailability(entity.isAvailable());
        if (entity.getState() != null) {
            model.setDeleted(entity.getState().isDeleted());
            model.setDeletedAt(entity.getState().getDeletedAt());
        }
        model.setProductSubtype(ProductSubtypeMapper.convertEntityToModel(entity.getProductSubtype()));
        model.setRate(entity.getProductStatistics().getRate());
        model.setRatings(entity.getProductStatistics().getRatings());
        model.setInWishList(entity.getProductStatistics().getInWishList());
        model.setProductImages(images);
        return model;
    }

    public static Product convertModelToEntity(ProductModel model) {
        return new Product(new ProductID(model.getProductId()),
                model.getDescription(), model.getName(), model.getPrice(),
                new Discount(getByDiscount(model.getDiscount()),
                        model.getDiscountStart(), model.getDiscountEnd()),
                model.getPriceWithDiscount(), model.getQuantity(), model.isAvailability(),
                new State(model.isDeleted(), model.getDeletedAt()),
                ProductSubtypeMapper.convertModelToEntity(model.getProductSubtype()),
                new ProductStatistics(model.getRate(), model.getRatings(), model.getInWishList()));
    }

    public static ProductDTO convertModelToProductDTO(ProductModel model) {
        return new ProductDTO(model.getProductId(), model.getDescription(),
                model.getName(), model.getPrice(), new DiscountDTO(model.getDiscount(),
                    model.getDiscountStart(), model.getDiscountEnd(), model.isDiscountExpired()),
                model.getPriceWithDiscount(), ProductSubtypeMapper.convertModelToProductTypeDTO(model.getProductSubtype().getProductType()),
                new ProductStatisticsDTO(model.getRate(), model.getRatings(), model.getInWishList()),
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
                entity.getActualPrice(), ProductSubtypeMapper.convertEntityToProductTypeDTO(entity.getProductSubtype()),
                new ProductStatisticsDTO(entity.getProductStatistics().getRate(), entity.getProductStatistics().getRatings(),
                        entity.getProductStatistics().getInWishList()),
                entity.getQuantity(), entity.isAvailable()
        );
    }

    public static GetProductDTO convertModelToGetProductDTO(ProductModel model) {
        List<ProductImageDTO> productImages = new ArrayList<>();
        if (model.getProductImages() != null || !model.getProductImages().isEmpty()) {
            for (ProductImageModel image : model.getProductImages()) {
                productImages.add(new ProductImageDTO(image.getImageName(), image.getImageName()));
            }
        }
        return new GetProductDTO(model.getProductId(), model.getDescription(),
                model.getName(), model.getPrice(), new DiscountDTO(model.getDiscount(),
                model.getDiscountStart(), model.getDiscountEnd(), model.isDiscountExpired()),
                model.getPriceWithDiscount(), model.getQuantity(), model.isAvailability(),
                ProductSubtypeMapper.convertModelToProductTypeDTO(model.getProductSubtype().getProductType()),
                new ProductStatisticsDTO(model.getRate(), model.getRatings(), model.getInWishList()),
                productImages,
                model.getCreatedAt(), model.getUpdatedAt()
        );
    }
}
