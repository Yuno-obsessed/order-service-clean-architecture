package sanity.nil.order.application.product.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.command.CreateProductCommandDTO;
import sanity.nil.order.application.product.interfaces.persistence.DiscountReader;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;

@RequiredArgsConstructor
public class CreateProductCommand {

    private final ProductDAO productDAO;
    private final ProductSubtypeReader productSubtypeReader;
    private final DiscountReader discountReader;
    private final ProductService service;

    public Product handle(CreateProductCommandDTO dto) {
        Product product = service.create(dto.description, dto.name, dto.price,
                discountReader.getByID(dto.discountID), dto.quantity,
                productSubtypeReader.getBySubtypeId(dto.subTypeID));
        return productDAO.createProduct(product);
    }
}
