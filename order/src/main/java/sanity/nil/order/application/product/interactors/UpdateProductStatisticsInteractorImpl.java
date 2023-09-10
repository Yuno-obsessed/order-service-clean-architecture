package sanity.nil.order.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.statistics.ProductStatisticsDTO;
import sanity.nil.order.application.product.dto.statistics.UpdateProductStatisticsDTO;
import sanity.nil.order.application.product.interfaces.interactors.UpdateProductStatisticsInteractor;
import sanity.nil.order.application.product.interfaces.query.ProductDAO;
import sanity.nil.order.application.product.interfaces.query.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;

@RequiredArgsConstructor
public class UpdateProductStatisticsInteractorImpl implements UpdateProductStatisticsInteractor {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService service;

    @Override
    public ProductStatisticsDTO addRating(UpdateProductStatisticsDTO updateStatisticsDTO) {
        Product product = productReader.getProductById(updateStatisticsDTO.productId);
        product = service.addRating(product, updateStatisticsDTO.addRate);
        productDAO.updateProduct(product, null);
        return productReader.getProductDTOById(updateStatisticsDTO.productId).productStatistics;
    }

    @Override
    public ProductStatisticsDTO addToWishList(UpdateProductStatisticsDTO updateStatisticsDTO) {
        Product product = productReader.getProductById(updateStatisticsDTO.productId);
        product = service.updateInWishList(product);
        productDAO.updateProduct(product, null);
        return productReader.getProductDTOById(updateStatisticsDTO.productId).productStatistics;
    }
}
