package sanity.nil.order.application.product.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.command.DeleteProductCommand;
import sanity.nil.order.application.product.command.CreateProductCommand;
import sanity.nil.order.application.product.command.UpdateProductCommand;
import sanity.nil.order.application.product.command.UpdateProductStatisticsCommand;

@RequiredArgsConstructor
public class ProductCommandService {

    public final CreateProductCommand createProductCommand;
    public final UpdateProductCommand updateProductCommand;
    public final UpdateProductStatisticsCommand updateProductStatisticsCommand;
    public final DeleteProductCommand deleteProductCommand;
}
