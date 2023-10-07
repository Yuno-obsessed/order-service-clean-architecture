package sanity.nil.product.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.product.application.interfaces.persistence.ProductDAO;
import sanity.nil.product.application.interfaces.persistence.ProductReader;
import sanity.nil.product.domain.entity.Product;
import sanity.nil.product.domain.service.ProductService;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteProductCommand {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService productService;

    public UUID handle(UUID id) {
        Product product = productService.delete(productReader.getProductById(id));
        productDAO.updateProduct(product, null);
        return id;
    }
}
