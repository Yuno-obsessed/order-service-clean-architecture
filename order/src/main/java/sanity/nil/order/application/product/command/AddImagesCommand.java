package sanity.nil.order.application.product.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.application.interfaces.storage.FileStorage;
import sanity.nil.order.application.product.dto.boundary.ProductFileData;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.service.ProductService;

@RequiredArgsConstructor
public class AddImagesCommand {

    private final ProductDAO productDAO;
    private final ProductReader productReader;
    private final ProductService productService;
    private final FileStorage fileStorage;

    public Product handle(ProductFileData dto) {
        Product product = productReader.getProductById(dto.getProductID());
        String bucketName = fileStorage.createBucketIfNotExists(product.getProductSubtype().getProductType().getProductTypeName());
        product = productService.addImages(product, dto.getFileNames(), bucketName);
        for (int i = 0; i < dto.getFiles().size(); i++) {
            fileStorage.saveFile(dto.getFiles().get(i),
                    product.getImages().getImageNames().get(i), bucketName);
        }
        product = productDAO.updateProduct(product);
        return product;
    }
}
