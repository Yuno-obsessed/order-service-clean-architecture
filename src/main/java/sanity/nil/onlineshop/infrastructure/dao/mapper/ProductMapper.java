package sanity.nil.onlineshop.infrastructure.dao.mapper;

import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.infrastructure.model.ProductModel;

public class ProductMapper {

    public static ProductModel convertEntityToModel(Product product) {
        ProductModel model = new ProductModel();
        model.setProductId(product.getProductId().getId());
        model.setDescription(product.getDescription());
        model.setName(product.getName());
        model.setPrice(product.getPrice());
        if (product.getDiscount() != null) {
            model.setDiscount(DiscountMapper.convertEntityToModel(product.getDiscount()));
        }
        model.setPriceWithDiscount(product.getActualPrice());
        model.setQuantity(product.getQuantity());
        model.setAvailability(product.isAvailable());
        return model;
    }

    public static ProductDTO convertModelToProductDTO(ProductModel model) {
        return new ProductDTO(model.getProductId(), model.getDescription(),
                model.getName(), model.getPrice(), DiscountMapper.convertModelToDTO(model.getDiscount()),
                model.getPriceWithDiscount(), model.getQuantity(), model.isAvailability(),
                model.getCreatedAt(), model.getUpdatedAt()
        );
    }
}
