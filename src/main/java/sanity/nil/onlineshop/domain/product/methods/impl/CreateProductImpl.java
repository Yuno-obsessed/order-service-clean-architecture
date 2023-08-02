package sanity.nil.onlineshop.domain.product.methods.impl;


import sanity.nil.onlineshop.domain.product.entity.Discount;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedPriceException;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedQuantityException;
import sanity.nil.onlineshop.domain.product.methods.CreateProduct;
import sanity.nil.onlineshop.domain.product.vo.ProductID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static sanity.nil.onlineshop.domain.product.entity.Discount.DiscountType.BUY_TWO_PRICE_FOR_ONE;
import static sanity.nil.onlineshop.domain.product.entity.Discount.DiscountType.getByCode;

public class CreateProductImpl implements CreateProduct {

    @Override
    public Product create(String description, String name, BigDecimal price,
                   Integer discountCode, LocalDateTime startsAt, LocalDateTime endsAt, Integer quantity) {
        boolean available = false;
        int discountInPercents = 0;
        BigDecimal newPrice = price;
        Discount discount = null;
        if (discountCode != null) {
            LocalDateTime now = LocalDateTime.now();
            boolean expired = startsAt.isAfter(now) && endsAt.isBefore(now);
            discount = new Discount(
                    getByCode(discountCode),
                    startsAt,
                    endsAt,
                    expired
            );
        }
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
