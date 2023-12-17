package sanity.nil.order.application.product.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.command.UpdateProductRateDTO;
import sanity.nil.order.application.product.dto.query.ProductStatisticsQueryDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.entity.ProductStatistics;
import sanity.nil.order.domain.product.service.ProductService;

@RequiredArgsConstructor
public class UpdateProductStatisticsCommand {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService service;

    public ProductStatisticsQueryDTO handle(UpdateProductRateDTO updateProductRateDTO) {
        Product product = productReader.getProductById(updateProductRateDTO.productId);
        product = service.addRating(product, updateProductRateDTO.addRate);
        ProductStatistics updatedStatistics = productDAO.updateProduct(product).getProductStatistics();

        return new ProductStatisticsQueryDTO(updatedStatistics.getRate(), updatedStatistics.getRatings(),
                updatedStatistics.getInWishList());
    }

//    public ProductStatisticsQueryDTO handle(UpdateProductWishListDTO updateProductWishListDTO) {
//        Product product = productReader.getProductById(updateProductWishListDTO.productId);
//        product = service.updateInWishList(product);
//        return productDAO.updateProduct(product);
//    }
}
