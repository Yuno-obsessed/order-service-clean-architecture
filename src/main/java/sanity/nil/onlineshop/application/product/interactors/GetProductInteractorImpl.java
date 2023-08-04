package sanity.nil.onlineshop.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.dto.GetProductDTO;
import sanity.nil.onlineshop.application.product.interfaces.interactors.GetProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductInteractorImpl implements GetProductInteractor {

    private final ProductReader productReader;

    @Override
    public GetProductDTO getById(UUID id) {
        return productReader.getGETProductDTOById(id);
    }

}
