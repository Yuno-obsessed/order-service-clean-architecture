package sanity.nil.product.application.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.product.application.query.GetAllProductsQuery;
import sanity.nil.product.application.query.GetProductByIdQuery;
import sanity.nil.product.application.query.GetProductsByNameQuery;

@RequiredArgsConstructor
public class ProductQueryService {

    public final GetAllProductsQuery getAllProductsQuery;
    public final GetProductByIdQuery getProductByIdQuery;
    public final GetProductsByNameQuery getProductsByNameQuery;
}
