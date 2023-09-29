package sanity.nil.order.application.product.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.boundary.ProductDTO;
import sanity.nil.order.application.product.dto.command.CreateDiscountDTO;
import sanity.nil.order.application.product.dto.command.CreateProductCommandDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

@RequiredArgsConstructor
public class CreateProductCommand {

    private final ProductDAO productDAO;
    private final ProductSubtypeReader productSubtypeReader;
    private final ProductService service;

    public ProductDTO handle(CreateProductCommandDTO dto) {
        CreateDiscountDTO discountDTO = dto.discountDTO;
        Product product = service.create(dto.description, dto.name, dto.price,
                discountDTO.discountCode, discountDTO.startsAt, discountDTO.endsAt,
                dto.quantity, productSubtypeReader.getBySubtypeId(dto.subTypeId));
        product = productDAO.createProduct(product, dto.productImage.imageNames);
        return ProductMapper.convertEntityToBoundary(product);
    }
}