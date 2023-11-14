package sanity.nil.order.infrastructure.database.orm.mapper;


import sanity.nil.order.application.common.application.dto.FileData;
import sanity.nil.order.application.common.domain.vo.Deleted;
import sanity.nil.order.application.common.domain.vo.Discount;
import sanity.nil.order.application.product.dto.boundary.*;
import sanity.nil.order.application.product.dto.command.UploadProductImages;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductStatisticsQueryDTO;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.vo.ProductID;
import sanity.nil.order.domain.product.vo.ProductStatistics;
import sanity.nil.order.infrastructure.database.models.ProductImageModel;
import sanity.nil.order.infrastructure.database.models.ProductModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static sanity.nil.order.application.common.domain.vo.Discount.DiscountType.getByDiscount;


public class ProductMapper {

    public static ProductModel convertEntityToModel(Product entity) {
        ProductModel model = new ProductModel();
        model.setId(entity.getProductId().getId());
        model.setDescription(entity.getDescription());
        model.setName(entity.getName());
        model.setPrice(entity.getPrice());
        if (entity.getDiscount() != null) {
            model.setDiscount(entity.getDiscount().getDiscountType().getDiscount());
            model.setDiscountStart(entity.getDiscount().getStartsAt());
            model.setDiscountEnd(entity.getDiscount().getEndsAt());
        }
        model.setQuantity(entity.getQuantity());
        model.setAvailability(entity.isAvailable());
        if (entity.getDeleted() != null) {
            model.setDeleted(entity.getDeleted().isDeleted());
            model.setDeletedAt(entity.getDeleted().getDeletedAt());
        }
        model.setProductSubtype(ProductSubtypeMapper.convertEntityToModel(entity.getProductSubtype()));
        model.setRate(entity.getProductStatistics().getRate());
        model.setRatings(entity.getProductStatistics().getRatings());
        model.setInWishList(entity.getProductStatistics().getInWishList());
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
        return new Product(new ProductID(model.getId()),
                model.getDescription(), model.getName(), model.getPrice(),
                new Discount(getByDiscount(model.getDiscount()),
                        model.getDiscountStart(), model.getDiscountEnd()),
                 model.getQuantity(), model.isAvailability(),
                new Deleted(model.isDeleted(), model.getDeletedAt()),
                ProductSubtypeMapper.convertModelToEntity(model.getProductSubtype()),
                new ProductStatistics(model.getRate(), model.getRatings(), model.getInWishList()));
    }

    public static List<Product> convertModelsToEntities(List<ProductModel> models) {
       return models.stream()
               .map(ProductMapper::convertModelToEntity)
               .collect(Collectors.toList());
    }

    public static OrderProduct convertModelToOrderEntity(ProductModel model) {
        return new OrderProduct(model.getId(), model.getName(), model.getPrice(),
                new Discount(getByDiscount(model.getDiscount()),
                        model.getDiscountStart(), model.getDiscountEnd()), model.getQuantity());
    }

    public static List<OrderProduct> convertModelsToOrderEntities(List<ProductModel> models) {
        return models.stream()
                .map(ProductMapper::convertModelToOrderEntity)
                .collect(Collectors.toList());
    }

    public static ProductQueryDTO convertModelToProductQueryDTO(ProductModel model) {
        List<ProductImageDTO> productImages = new ArrayList<>();
        if (model.getProductImages() != null || !model.getProductImages().isEmpty()) {
            for (ProductImageModel image : model.getProductImages()) {
                productImages.add(new ProductImageDTO(image.getImageName(), image.getImageName()));
            }
        }
        return new ProductQueryDTO(model.getId(), model.getDescription(),
                model.getName(), model.getPrice(), new DiscountDTO(model.getDiscount(),
                model.getDiscountStart(), model.getDiscountEnd(), model.isDiscountExpired()),
                model.getQuantity(), model.isAvailability(),
                ProductSubtypeMapper.convertModelToProductTypeDTO(model.getProductSubtype().getProductType()),
                new ProductStatisticsDTO(model.getRate(), model.getRatings(), model.getInWishList()),
                productImages,
                model.getCreatedAt(), model.getUpdatedAt()
        );
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
        return new ProductStatisticsQueryDTO(product.getProductId().getId(),
                product.getProductStatistics().getRate(), product.getProductStatistics().getRatings(),
                product.getProductStatistics().getInWishList());
    }

    public static ProductStatisticsDTO convertEntityToProductStatisticsDTO(Product product) {
        return new ProductStatisticsDTO(product.getProductStatistics().getRate(),
                product.getProductStatistics().getRatings(),
                product.getProductStatistics().getInWishList());
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
}
