package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.interfaces.storage.FileStorage;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetProductByIdQuery {

    private final ProductReader productReader;
    private final FileStorage storage;

    public ProductQueryDTO handle(UUID id) {
        ProductQueryDTO productQuery = productReader.getProductQueryById(id);
        if (productQuery.productImages != null && !productQuery.productImages.isEmpty()) {
            productQuery.productImages = productQuery.productImages.stream().map(e -> {
                String[] nameAndBucket = e.split(",");
                e = storage.getFileURL(nameAndBucket[0], nameAndBucket[1]);
                return e;
            }).collect(Collectors.toList());
        }
        return productQuery;
    }
}
