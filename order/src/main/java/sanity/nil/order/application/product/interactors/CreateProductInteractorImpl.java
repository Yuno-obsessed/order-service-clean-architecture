package sanity.nil.order.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.CreateProductDTO;
import sanity.nil.order.application.product.dto.ProductDTO;
import sanity.nil.order.application.product.interfaces.interactors.CreateProductInteractor;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.application.product.interfaces.persistence.ProductSubtypeReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateProductInteractorImpl implements CreateProductInteractor {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductSubtypeReader productSubtypeReader;
    private final ProductService service;


    @Override
    public ProductDTO create(CreateProductDTO dto) {
        Integer discountCode = null;
        LocalDateTime startsAt = null;
        LocalDateTime endsAt = null;
        if (dto.discountDTO != null){
            discountCode = dto.discountDTO.discountCode;
            startsAt = dto.discountDTO.startsAt;
            endsAt = dto.discountDTO.endsAt;
        }
        Product product = service.create(dto.description, dto.name, dto.price,
                discountCode, startsAt, endsAt, dto.quantity,
                productSubtypeReader.getBySubtypeId(dto.subTypeId));
        product = productDAO.createProduct(product, dto.productImage.imageNames);
        return productReader.getProductDTOById(product.getProductId().getId());
    }
}
