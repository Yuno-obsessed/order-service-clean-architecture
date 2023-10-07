package sanity.nil.product.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.product.application.dto.command.CreateDiscountDTO;
import sanity.nil.product.application.dto.command.UpdateProductCommandDTO;
import sanity.nil.product.application.interfaces.persistence.ProductDAO;
import sanity.nil.product.application.interfaces.persistence.ProductReader;
import sanity.nil.product.application.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.product.domain.entity.Product;
import sanity.nil.product.domain.service.ProductService;

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
