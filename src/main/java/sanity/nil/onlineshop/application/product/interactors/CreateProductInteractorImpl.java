package sanity.nil.onlineshop.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.dto.CreateProductDTO;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductDAO;
import sanity.nil.onlineshop.application.product.interfaces.interactors.CreateProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.query.ProductReader;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.service.ProductService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateProductInteractorImpl implements CreateProductInteractor {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService service;


    @Override
    public ProductDTO create(CreateProductDTO dto) {
        Integer discountCode = null;
        LocalDateTime startsAt = null;
        LocalDateTime endsAt = null;
        if (dto.getDiscountDTO() != null){
            discountCode = dto.getDiscountDTO().getDiscountCode();
            startsAt = dto.getDiscountDTO().getStartsAt();
            endsAt = dto.getDiscountDTO().getEndsAt();
        }
        Product product = service.create(dto.getDescription(), dto.getName(), dto.getPrice(),
                discountCode, startsAt, endsAt, dto.getQuantity(), dto.getTypeId());
        product = productDAO.createProduct(product);
        return productReader.getProductDTOById(product.getProductId().getId());
    }
}
