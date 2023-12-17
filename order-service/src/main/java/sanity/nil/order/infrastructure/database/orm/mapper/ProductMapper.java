package sanity.nil.order.infrastructure.database.orm.mapper;


import sanity.nil.order.application.common.dto.FileData;
import sanity.nil.order.application.product.dto.boundary.ProductDTO;
import sanity.nil.order.application.product.dto.boundary.ProductFileData;
import sanity.nil.order.application.product.dto.boundary.ProductStatisticsDTO;
import sanity.nil.order.application.product.dto.command.UploadProductImages;
import sanity.nil.order.application.product.dto.query.DiscountQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductCardQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductStatisticsQueryDTO;
import sanity.nil.order.domain.common.entity.Discount;
import sanity.nil.order.domain.common.vo.Deleted;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.entity.ProductImages;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.entity.ProductStatistics;
import sanity.nil.order.domain.product.vo.ProductID;
import sanity.nil.order.infrastructure.database.models.DiscountModel;
import sanity.nil.order.infrastructure.database.models.ProductImageModel;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.models.ProductRateModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



public class ProductMapper {

    public static ProductModel convertEntityToModel(Product entity) {
        ProductModel model = new ProductModel();
        model.setId(entity.getProductId().getId());
        model.setDescription(entity.getDescription());
        model.setName(entity.getName());
        model.setPrice(entity.getPrice());
        if (entity.getDiscount() != null) {
            Discount discount = entity.getDiscount();
            model.setDiscount(new DiscountModel(discount.getDiscountID(), discount.getStartsAt(),
                    discount.getEndsAt(), discount.getPercent(), discount.isActive()));
        }
        model.setQuantity(entity.getQuantity());
        model.setAvailability(entity.isAvailable());
        if (entity.getDeleted() != null) {
            model.setDeleted(entity.getDeleted().isDeleted());
            model.setDeletedAt(entity.getDeleted().getDeletedAt());
        }
        model.setProductSubtype(ProductSubtypeMapper.convertEntityToModel(entity.getProductSubtype()));
        if (entity.getProductStatistics() != null) {
            model.setProductRate(new ProductRateModel(model.getId(), entity.getProductStatistics().getRate(),
                    entity.getProductStatistics().getRatings(), model));
        }
        List<ProductImageModel> imageModels = new ArrayList<>();
        if (entity.getImages() != null) {
            for (String imageName : entity.getImages().getImageNames()) {
                imageModels.add(new ProductImageModel(UUID.randomUUID(), imageName,
                        entity.getImages().getBucketName()));
            }
        }
        model.setProductImages(imageModels);
        return model;
    }

    public static Product convertModelToEntity(ProductModel model) {
        Discount discount = null;
        if (model.getDiscount() != null) {
            DiscountModel discountModel = model.getDiscount();
            discount = new Discount(discountModel.getDiscountID(), discountModel.getPercent(),
                    discountModel.getStartedAt(), discountModel.getExpiredAt());
        }
        return new Product(new ProductID(model.getId()),
                model.getDescription(), model.getName(), model.getPrice(),
                discount, model.getQuantity(), model.isAvailability(),
                new Deleted(model.isDeleted(), model.getDeletedAt()),
                ProductSubtypeMapper.convertModelToEntity(model.getProductSubtype()),
                new ProductStatistics(model.getProductRate().getRate(), model.getProductRate().getRatings(),
                        null));
    }

    public static List<Product> convertModelsToEntities(List<ProductModel> models) {
       return models.stream()
               .map(ProductMapper::convertModelToEntity)
               .collect(Collectors.toList());
    }

    public static OrderProduct convertModelToOrderEntity(ProductModel model) {
        Discount discount = null;
        if (model.getDiscount() != null) {
            DiscountModel discountModel = model.getDiscount();
            discount = convertDiscountModelToEntity(discountModel);
        }
        return new OrderProduct(model.getId(), model.getName(), model.getPrice(), discount, model.getQuantity());
    }

    public static List<OrderProduct> convertModelsToOrderEntities(List<ProductModel> models) {
        return models.stream()
                .map(ProductMapper::convertModelToOrderEntity)
                .collect(Collectors.toList());
    }

    public static ProductQueryDTO convertModelToProductQueryDTO(ProductModel model) {
        List<String> productImages = new ArrayList<>();
        if (model.getProductImages() != null && !model.getProductImages().isEmpty()) {
            model.getProductImages().forEach(e -> productImages.add(e.getImageName() + ',' + e.getBucketName()));
        }
        return new ProductQueryDTO(model.getId(), model.getDescription(),
                model.getName(), model.getPrice(), model.getDiscount() == null ? null : convertDiscountEntityToQuery(model.getDiscount()),
                model.getQuantity(), model.isAvailability(),
                ProductSubtypeMapper.convertModelToProductTypeDTO(model.getProductSubtype().getProductType()),
                new ProductStatisticsDTO(model.getProductRate().getRate(), model.getProductRate().getRatings(), null),
                productImages, model.getCreatedAt(), model.getUpdatedAt()
        );
    }

    public static ProductCardQueryDTO convertModelToProductCardQueryDTO(ProductModel model) {
        Integer discount = null;
        if (model.getDiscount() != null && model.getDiscount().isActive()) {
           discount = model.getDiscount().getPercent();
        }
        ProductImageModel productImage = null;
        if (model.getProductImages() != null && !model.getProductImages().isEmpty()) {
            productImage = model.getProductImages().stream()
                    .filter(e -> e.getImageName().lastIndexOf('0') == e.getImageName().length() - 1)
                    .findFirst()
                    .orElse(null);
        }
        return new ProductCardQueryDTO(model.getId(), model.getName(), model.getProductRate().getRate(),
                model.getProductRate().getRatings(),
                model.getPrice(), discount, null, productImage != null ?
                productImage.getImageName() + ',' + productImage.getBucketName() : null
        );
    }

    public static List<ProductCardQueryDTO> convertListOfModelsToProductCardQueryDTOs(List<ProductModel> models) {
        return models.stream()
                .map(ProductMapper::convertModelToProductCardQueryDTO)
                .collect(Collectors.toList());
    }

    public static List<ProductQueryDTO> convertListOfModelsToProductQueryDTOs(List<ProductModel> models) {
        return models.stream()
                .map(ProductMapper::convertModelToProductQueryDTO)
                .collect(Collectors.toList());
    }

    public static ProductDTO convertEntityToBoundary(Product product) {
        return new ProductDTO(product.getProductId().getId());
    }

    public static ProductStatisticsQueryDTO convertEntityToProductStatisticsQueryDTO(Product product) {
        return new ProductStatisticsQueryDTO(product.getProductStatistics().getRate(),
                product.getProductStatistics().getRatings(), product.getProductStatistics().getInWishList());
    }

    public static ProductStatisticsDTO convertEntityToProductStatisticsDTO(Product product) {
        return new ProductStatisticsDTO(product.getProductStatistics().getRate(),
                product.getProductStatistics().getRatings(),
                product.getProductStatistics().getInWishList());
    }

    public static DiscountQueryDTO convertDiscountEntityToQuery(DiscountModel discount) {
        return new DiscountQueryDTO(discount.getPercent(), discount.getStartedAt(), discount.getExpiredAt(), !discount.isActive());
    }

    public static Discount convertDiscountModelToEntity(DiscountModel model) {
        return new Discount(model.getDiscountID(), model.getPercent(),
                model.getStartedAt(), model.getExpiredAt());
    }

    public static ProductFileData convertUploadImagesToFileData(UploadProductImages dto) {
        return new ProductFileData(dto.getProductID(), dto.getFiles().stream().map(e ->
        {
            try {
                return new FileData(e.getInputStream(), e.getOriginalFilename(), e.getContentType(), e.getSize());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(Collectors.toList()));
    }

    public static ProductImages convertProductImagesModelToEntity(List<ProductImageModel> imageModels) {
        if (imageModels == null || imageModels.isEmpty()) {
            return null;
        }
        return new ProductImages(imageModels.stream()
                .map(ProductImageModel::getImageName)
                .toList(), imageModels.get(0).getBucketName()
        );
    }
}
