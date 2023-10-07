package sanity.nil.product.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.product.application.dto.command.CreateDiscountDTO;
import sanity.nil.product.application.dto.command.CreateProductCommandDTO;
import sanity.nil.product.application.interfaces.persistence.ProductDAO;
import sanity.nil.product.application.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.product.domain.entity.Product;
import sanity.nil.product.domain.service.ProductService;

@RequiredArgsConstructor
public class CreateProductCommand {

    private final ProductDAO productDAO;
    private final ProductSubtypeReader productSubtypeReader;
    private final ProductService service;

    public Product handle(CreateProductCommandDTO dto) {
        CreateDiscountDTO discountDTO = dto.discountDTO;
        Product product = service.create(dto.description, dto.name, dto.price,
                discountDTO.discountCode, discountDTO.startsAt, discountDTO.endsAt,
                dto.quantity, productSubtypeReader.getBySubtypeId(dto.subTypeId));
        return productDAO.createProduct(product, dto.productImage.imageNames);
    }
}
