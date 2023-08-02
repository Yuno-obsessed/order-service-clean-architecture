package sanity.nil.onlineshop.application.product.interfaces.interactors;

import sanity.nil.onlineshop.application.product.dto.CreateProductDTO;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;

public interface CreateProductInteractor {

    ProductDTO create(CreateProductDTO dto);
}
