package sanity.nil.order.application.product.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.query.GetAllImagesQuery;
import sanity.nil.order.application.product.query.GetAllProductsQuery;
import sanity.nil.order.application.product.query.GetProductByIdQuery;
import sanity.nil.order.application.product.query.GetProductsByNameQuery;

@RequiredArgsConstructor
public class ProductQueryService {

    public final GetAllProductsQuery getAllProductsQuery;
    public final GetProductByIdQuery getProductByIdQuery;
    public final GetProductsByNameQuery getProductsByNameQuery;
    public final GetAllImagesQuery getAllImagesQuery;
}
