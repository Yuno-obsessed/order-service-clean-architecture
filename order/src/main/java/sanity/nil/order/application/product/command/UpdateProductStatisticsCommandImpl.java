package sanity.nil.order.application.product.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.command.UpdateProductStatisticsDTO;
import sanity.nil.order.application.product.dto.query.ProductStatisticsQueryDTO;
import sanity.nil.order.application.product.interfaces.command.UpdateProductStatisticsCommand;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

@RequiredArgsConstructor
public class UpdateProductStatisticsCommandImpl implements UpdateProductStatisticsCommand {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService service;

    @Override
    public ProductStatisticsQueryDTO addRating(UpdateProductStatisticsDTO updateStatisticsDTO) {
        Product product = productReader.getProductById(updateStatisticsDTO.productId);
        product = service.addRating(product, updateStatisticsDTO.addRate);
        productDAO.updateProduct(product, null);
        return ProductMapper.convertEntityToProductStatisticsQueryDTO(product);
    }

    @Override
    public ProductStatisticsQueryDTO addToWishList(UpdateProductStatisticsDTO updateStatisticsDTO) {
        Product product = productReader.getProductById(updateStatisticsDTO.productId);
        product = service.updateInWishList(product);
        productDAO.updateProduct(product, null);
        return ProductMapper.convertEntityToProductStatisticsQueryDTO(product);
    }
}
