package sanity.nil.order.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.GetProductDTO;
import sanity.nil.order.application.product.interfaces.interactors.GetProductInteractor;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductInteractorImpl implements GetProductInteractor {

    private final ProductReader productReader;

    @Override
    public GetProductDTO getById(UUID id) {
        return productReader.getGETProductDTOById(id);
    }

}
