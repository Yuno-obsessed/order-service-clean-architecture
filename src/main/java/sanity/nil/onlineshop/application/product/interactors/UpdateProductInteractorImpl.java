package sanity.nil.onlineshop.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.dto.UpdateProductDTO;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductDAO;
import sanity.nil.onlineshop.application.product.interfaces.interactors.UpdateProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductReader;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductSubtypeReader;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.service.ProductService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UpdateProductInteractorImpl implements UpdateProductInteractor {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductSubtypeReader productSubtypeReader;
    private final ProductService service;

    @Override
    public ProductDTO update(UpdateProductDTO dto) {
        Integer discountCode = null;
        LocalDateTime startsAt = null;
        LocalDateTime endsAt = null;
        if (dto.discountDTO != null){
            discountCode = dto.discountDTO.discountCode;
            startsAt = dto.discountDTO.startsAt;
            endsAt = dto.discountDTO.endsAt;
        }
        Product product = service.update(dto.id, dto.description, dto.name, dto.price,
                discountCode, startsAt, endsAt, dto.quantity,
                productSubtypeReader.getBySubtypeId(dto.typeId));
        product = productDAO.updateProduct(product);
        return productReader.getProductDTOById(product.getProductId().getId());
    }
}
