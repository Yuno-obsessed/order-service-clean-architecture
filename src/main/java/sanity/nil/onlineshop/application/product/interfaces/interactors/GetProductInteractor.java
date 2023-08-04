package sanity.nil.onlineshop.application.product.interfaces.interactors;

import sanity.nil.onlineshop.application.product.dto.GetProductDTO;

import java.util.UUID;

public interface GetProductInteractor {

    GetProductDTO getById(UUID id);
}
