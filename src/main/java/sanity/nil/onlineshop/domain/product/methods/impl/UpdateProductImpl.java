package sanity.nil.onlineshop.domain.product.methods.impl;

import sanity.nil.onlineshop.domain.product.entity.Discount;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedPriceException;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedQuantityException;
import sanity.nil.onlineshop.domain.product.methods.UpdateProduct;
import sanity.nil.onlineshop.domain.product.vo.ProductID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static sanity.nil.onlineshop.domain.product.entity.Discount.DiscountType.BUY_TWO_PRICE_FOR_ONE;

public class UpdateProductImpl implements UpdateProduct {

    @Override
    public Product update(ProductID id, String description, String name, BigDecimal price,
                          Discount discount, Integer quantity) {
        boolean available = false;
        int discountInPercents = 0;
        BigDecimal newPrice = price;
        if (quantity < 0) {
            throw UnsupportedQuantityException.throwEx(quantity);
        } else if (quantity > 0){
           available = true;
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw UnsupportedPriceException.throwEx(price);
        }
        if (discount != null && discount.getStartsAt().isBefore(LocalDateTime.now()) &&
                discount.getEndsAt().isAfter(LocalDateTime.now())){
            if (discount.getDiscountType() == BUY_TWO_PRICE_FOR_ONE) {
                //TODO : implement
            } else {
               discountInPercents = discount.getDiscountType().getDiscount();
               newPrice = price
                       .subtract(
                               price
                                       .divide(BigDecimal.valueOf(100L))
                                       .multiply(BigDecimal.valueOf(discountInPercents))
               );
            }
        }


        return new Product(new ProductID(), description, name, price, discount, newPrice, quantity, available);
    }
}
