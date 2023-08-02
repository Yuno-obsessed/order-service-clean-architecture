package sanity.nil.onlineshop.application.product.interfaces.interactors;

import sanity.nil.onlineshop.application.product.dto.ProductDTO;

import java.util.UUID;

public interface GetProductInteractor {

    ProductDTO getById(UUID id);
}
