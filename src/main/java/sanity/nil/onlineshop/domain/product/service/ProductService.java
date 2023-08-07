package sanity.nil.onlineshop.domain.product.service;


import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.domain.product.consts.ProductType;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedPriceException;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedQuantityException;
import sanity.nil.onlineshop.domain.product.vo.Discount;
import sanity.nil.onlineshop.domain.product.vo.ProductID;
import sanity.nil.onlineshop.domain.product.vo.ProductSubtype;
import sanity.nil.onlineshop.domain.product.vo.State;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static sanity.nil.onlineshop.domain.product.vo.Discount.DiscountType.BUY_TWO_PRICE_FOR_ONE;
import static sanity.nil.onlineshop.domain.product.vo.Discount.DiscountType.getByCode;

public class ProductService {

    public Product create(String description, String name, BigDecimal price,
                          Integer discountCode, LocalDateTime startsAt,
                          LocalDateTime endsAt, Integer quantity, ProductSubtype productSubtype) {
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


        return new Product(new ProductID(), description, name, price, discount,
                newPrice, quantity, available, new State(false), productSubtype);
    }

    public Product update(UUID id, String description, String name, BigDecimal price,
                          Integer discountCode, LocalDateTime startsAt,
                          LocalDateTime endsAt, Integer quantity, ProductSubtype productSubtype) {
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


        return new Product(new ProductID(id), description, name, price, discount,
                newPrice, quantity, available, new State(false), productSubtype);
    }

    public Product delete(Product product) {
        product.setState(new State(true));
        return product;
    }

}
