package sanity.nil.onlineshop.domain.product.methods;

import sanity.nil.onlineshop.domain.product.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CreateProduct {

    Product create(String description, String name, BigDecimal price,
                   Integer discountCode, LocalDateTime startsAt, LocalDateTime endsAt, Integer quantity);
}
