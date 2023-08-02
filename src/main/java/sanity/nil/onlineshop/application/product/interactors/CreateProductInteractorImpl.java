package sanity.nil.onlineshop.application.product.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.dto.CreateProductDTO;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.interfaces.ProductRepository;
import sanity.nil.onlineshop.application.product.interfaces.interactors.CreateProductInteractor;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.methods.CreateProduct;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateProductInteractorImpl implements CreateProductInteractor {

    private final ProductRepository productRepository;
    private final CreateProduct createMethod;


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
        Product product = createMethod.create(dto.getDescription(), dto.getName(), dto.getPrice(),
                discountCode, startsAt, endsAt, dto.getQuantity());
        return productRepository.createProduct(product);
    }
}
