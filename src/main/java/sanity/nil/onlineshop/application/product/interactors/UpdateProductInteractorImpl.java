package sanity.nil.onlineshop.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.dto.UpdateProductDTO;
import sanity.nil.onlineshop.application.product.interfaces.ProductRepository;
import sanity.nil.onlineshop.application.product.interfaces.interactors.UpdateProductInteractor;
import sanity.nil.onlineshop.domain.product.methods.UpdateProduct;

@RequiredArgsConstructor
public class UpdateProductInteractorImpl implements UpdateProductInteractor {

    private final ProductRepository productRepository;
    private final UpdateProduct updateMethod;

    @Override
    public ProductDTO update(UpdateProductDTO dto) {
//        Product product = updateMethod.update(dto.getId(), dto.getDescription(), dto.getName());
//        return productRepository.updateProduct(product);
        return null;
    }
}
