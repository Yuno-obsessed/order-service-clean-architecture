package sanity.nil.order.application.product.interfaces.interactors;


import sanity.nil.order.application.product.dto.GetProductDTO;

import java.util.UUID;

public interface GetProductInteractor {

    GetProductDTO getById(UUID id);
}
