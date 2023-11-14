package sanity.nil.order.application.product.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.command.*;

@RequiredArgsConstructor
public class ProductCommandService {

    public final CreateProductCommand createProductCommand;
    public final UpdateProductCommand updateProductCommand;
    public final UpdateProductStatisticsCommand updateProductStatisticsCommand;
    public final DeleteProductCommand deleteProductCommand;
    public final AddImagesCommand addImagesCommand;
}
