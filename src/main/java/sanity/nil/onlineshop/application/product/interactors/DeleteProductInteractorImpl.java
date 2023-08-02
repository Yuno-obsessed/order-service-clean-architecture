package sanity.nil.onlineshop.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.interfaces.ProductRepository;
import sanity.nil.onlineshop.application.product.interfaces.interactors.DeleteProductInteractor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteProductInteractorImpl implements DeleteProductInteractor {

    private final ProductRepository productRepository;

    @Override
    public UUID delete(UUID id) {
        productRepository.deleteByProductId(id);
        return id;
    }
}
