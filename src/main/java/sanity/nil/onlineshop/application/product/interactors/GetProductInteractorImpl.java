package sanity.nil.onlineshop.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.interfaces.ProductRepository;
import sanity.nil.onlineshop.application.product.interfaces.interactors.GetProductInteractor;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductInteractorImpl implements GetProductInteractor {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO getById(UUID id) {
        return productRepository.getByProductId(id);
    }

}
