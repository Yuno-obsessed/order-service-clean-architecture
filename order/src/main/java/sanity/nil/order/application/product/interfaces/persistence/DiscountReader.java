package sanity.nil.order.application.product.interfaces.persistence;

import sanity.nil.order.domain.common.entity.Discount;

import java.util.UUID;

public interface DiscountReader {

    Discount getByID(UUID id);
}
