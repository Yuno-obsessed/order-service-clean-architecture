package sanity.nil.order.application.product.interfaces.interactors;


import sanity.nil.order.application.product.dto.ProductDTO;
import sanity.nil.order.application.product.dto.UpdateProductDTO;

public interface UpdateProductInteractor {

    ProductDTO update(UpdateProductDTO dto);
}
