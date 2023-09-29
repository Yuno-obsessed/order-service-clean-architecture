package sanity.nil.order.application.product.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.boundary.ProductStatisticsDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductRateDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductWishListDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

@RequiredArgsConstructor
public class UpdateProductStatisticsCommand {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService service;

    public ProductStatisticsDTO handle(UpdateProductRateDTO updateProductRateDTO) {
        Product product = productReader.getProductById(updateProductRateDTO.productId);
        product = service.addRating(product, updateProductRateDTO.addRate);
        productDAO.updateProduct(product, null);
        return ProductMapper.convertEntityToProductStatisticsDTO(product);
    }

    public ProductStatisticsDTO handle(UpdateProductWishListDTO updateProductWishListDTO) {
        Product product = productReader.getProductById(updateProductWishListDTO.productId);
        product = service.updateInWishList(product);
        productDAO.updateProduct(product, null);
        return ProductMapper.convertEntityToProductStatisticsDTO(product);
    }
}
