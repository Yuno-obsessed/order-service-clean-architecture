package sanity.nil.onlineshop.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductDAO;
import sanity.nil.onlineshop.application.product.interfaces.interactors.DeleteProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductReader;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.service.ProductService;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteProductInteractorImpl implements DeleteProductInteractor {

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
