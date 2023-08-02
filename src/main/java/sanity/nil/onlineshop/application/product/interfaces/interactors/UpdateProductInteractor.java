package sanity.nil.onlineshop.application.product.interfaces.interactors;

import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.dto.UpdateProductDTO;

public interface UpdateProductInteractor {

    ProductDTO update(UpdateProductDTO dto);
}
