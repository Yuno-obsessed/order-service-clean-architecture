package sanity.nil.product.application.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.product.application.command.DeleteProductCommand;
import sanity.nil.product.application.command.CreateProductCommand;
import sanity.nil.product.application.command.UpdateProductCommand;
import sanity.nil.product.application.command.UpdateProductStatisticsCommand;

@RequiredArgsConstructor
public class ProductCommandService {

    public final CreateProductCommand createProductCommand;
    public final UpdateProductCommand updateProductCommand;
    public final UpdateProductStatisticsCommand updateProductStatisticsCommand;
    public final DeleteProductCommand deleteProductCommand;
}
