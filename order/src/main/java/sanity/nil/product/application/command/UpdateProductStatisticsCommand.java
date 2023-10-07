package sanity.nil.product.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.product.application.dto.command.UpdateProductRateDTO;
import sanity.nil.product.application.dto.command.UpdateProductWishListDTO;
import sanity.nil.product.application.interfaces.persistence.ProductDAO;
import sanity.nil.product.application.interfaces.persistence.ProductReader;
import sanity.nil.product.domain.entity.Product;
import sanity.nil.product.domain.service.ProductService;

@RequiredArgsConstructor
public class UpdateProductStatisticsCommand {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService service;

    public Product handle(UpdateProductRateDTO updateProductRateDTO) {
        Product product = productReader.getProductById(updateProductRateDTO.productId);
        product = service.addRating(product, updateProductRateDTO.addRate);
        return productDAO.updateProduct(product, null);
    }

    public Product handle(UpdateProductWishListDTO updateProductWishListDTO) {
        Product product = productReader.getProductById(updateProductWishListDTO.productId);
        product = service.updateInWishList(product);
        return productDAO.updateProduct(product, null);
    }
}
