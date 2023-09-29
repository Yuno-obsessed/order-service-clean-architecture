package sanity.nil.order.application.product.interfaces.command;

import sanity.nil.order.application.product.dto.boundary.ProductDTO;
import sanity.nil.order.application.product.dto.command.CreateProductCommandDTO;

public interface CreateProductCommand {

    ProductDTO create(CreateProductCommandDTO dto);
}
