package sanity.nil.order.application.product.interfaces.persistence;

import sanity.nil.order.domain.order.entity.ProductImages;

import java.util.UUID;

public interface ProductImageReader {

    ProductImages getProductImagesByID(UUID productID);
}
