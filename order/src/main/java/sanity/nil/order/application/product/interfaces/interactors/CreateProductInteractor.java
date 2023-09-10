package sanity.nil.order.application.product.interfaces.interactors;


import sanity.nil.order.application.product.dto.CreateProductDTO;
import sanity.nil.order.application.product.dto.ProductDTO;

public interface CreateProductInteractor {

    ProductDTO create(CreateProductDTO dto);
}
