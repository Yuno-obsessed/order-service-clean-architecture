package sanity.nil.order.application.product.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.interfaces.storage.FileStorage;
import sanity.nil.order.application.product.dto.boundary.ProductImageInfo;
import sanity.nil.order.application.product.interfaces.persistence.ProductImageReader;
import sanity.nil.order.domain.order.entity.ProductImages;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetAllImagesQuery {

    private final ProductImageReader productImageReader;
    private final FileStorage fileStorage;

    public List<ProductImageInfo> handle(UUID productID, Boolean onlyMain) {
        ProductImages images = productImageReader.getProductImagesByID(productID);
        List<ProductImageInfo> productImageList = new ArrayList<>();
        if (images == null) {
            return null;
        }
        if (onlyMain != null && onlyMain) {
            String mainImage = images.getImageNames().stream()
                    .filter(e -> e.lastIndexOf('0') == e.length() - 1)
                    .findFirst()
                    .get();
            productImageList.add(new ProductImageInfo(mainImage, fileStorage.getFileURL(mainImage, images.getBucketName())));
            return productImageList;
        }
        for (String image : images.getImageNames()) {
            productImageList.add(new ProductImageInfo(image, fileStorage.getFileURL(image, images.getBucketName())));
        }
        return productImageList;
    }
}
