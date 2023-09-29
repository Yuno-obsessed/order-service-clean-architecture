package sanity.nil.order.application.product.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.interfaces.command.DeleteProductCommand;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteProductCommandImpl implements DeleteProductCommand {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService productService;

    @Override
    public UUID delete(UUID id) {
        Product product = productService.delete(productReader.getProductById(id));
        productDAO.updateProduct(product, null);
        return id;
    }
}
