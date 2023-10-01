package sanity.nil.order.application.product.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.command.CreateDiscountDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductCommandDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.application.product.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;

@RequiredArgsConstructor
public class UpdateProductCommand {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductSubtypeReader productSubtypeReader;
    private final ProductService service;

    public Product handle(UpdateProductCommandDTO dto) {
        CreateDiscountDTO discountDTO = dto.discountDTO;
        Product product = productReader.getProductById(dto.id);
        product = service.update(dto.id, dto.description, dto.name, dto.price,
                discountDTO.discountCode, discountDTO.startsAt, discountDTO.endsAt,
                dto.quantity, productSubtypeReader.getBySubtypeId(dto.typeId),
                product.getProductStatistics());
        return productDAO.updateProduct(product, dto.productImage.imageNames);
    }
}
