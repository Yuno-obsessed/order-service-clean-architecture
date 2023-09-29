package sanity.nil.order.application.product.interfaces.command;

import sanity.nil.order.application.product.dto.boundary.ProductDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductCommandDTO;

public interface UpdateProductCommand {

    ProductDTO update(UpdateProductCommandDTO dto);
}
