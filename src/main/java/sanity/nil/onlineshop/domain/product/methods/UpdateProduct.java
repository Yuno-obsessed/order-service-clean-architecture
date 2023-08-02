package sanity.nil.onlineshop.domain.product.methods;

import sanity.nil.onlineshop.domain.product.entity.Discount;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.vo.ProductID;

import java.math.BigDecimal;

public interface UpdateProduct {

    Product update(ProductID id, String description, String name,
                   BigDecimal price, Discount discount, Integer quantity);
}
