package sanity.nil.order.domain.product.service;


import sanity.nil.order.domain.common.entity.Discount;
import sanity.nil.order.domain.common.vo.Deleted;
import sanity.nil.order.domain.order.entity.ProductImages;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.entity.ProductStatistics;
import sanity.nil.order.domain.product.entity.ProductSubtype;
import sanity.nil.order.domain.product.exceptions.UnsupportedPriceException;
import sanity.nil.order.domain.product.exceptions.UnsupportedQuantityException;
import sanity.nil.order.domain.product.exceptions.UnsupportedRateException;
import sanity.nil.order.domain.product.vo.ProductID;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class ProductService {

    public Product create(String description, String name, BigDecimal price,
                          Discount discount, Integer quantity, ProductSubtype productSubtype) {
        boolean available = false;
        BigDecimal newPrice = price;
        if (quantity < 0) {
            throw UnsupportedQuantityException.throwEx(quantity);
        } else if (quantity > 0){
            available = true;
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw UnsupportedPriceException.throwEx(price);
        }
        if (discount.isActive()) {
            newPrice = price
                    .subtract(
                            price
                                    .divide(BigDecimal.valueOf(100L))
                                    .multiply(BigDecimal.valueOf(discount.getPercent()))
                    );
        }


        return new Product(new ProductID(), description, name, price, discount,
                quantity, available, new Deleted(), productSubtype,
                new ProductStatistics(BigDecimal.ZERO, 0, 0));
    }

    public Product update(UUID id, String description, String name, BigDecimal price,
                          Discount discount, Integer quantity, ProductSubtype productSubtype,
                          ProductStatistics productStatistics) {
        boolean available = false;
        BigDecimal newPrice = price;
        if (quantity < 0) {
            throw UnsupportedQuantityException.throwEx(quantity);
        } else if (quantity > 0){
            available = true;
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw UnsupportedPriceException.throwEx(price);
        }
        if (discount.isActive()) {
            newPrice = price
                    .subtract(
                            price
                                    .divide(BigDecimal.valueOf(100L))
                                    .multiply(BigDecimal.valueOf(discount.getPercent()))
                    );
        }

        return new Product(new ProductID(id), description, name, price, discount,
                quantity, available, new Deleted(), productSubtype,
                productStatistics);
    }

    public Product addRating(Product product, BigDecimal addedRating) {
        if (addedRating.compareTo(BigDecimal.valueOf(5)) > 0 || addedRating.compareTo(BigDecimal.ONE) < 0) {
            throw UnsupportedRateException.throwEx(addedRating);
        }
        ProductStatistics productStatistics = Objects.requireNonNull(product.getProductStatistics());
        BigDecimal rate = productStatistics.getRate()
                .multiply(BigDecimal.valueOf(productStatistics.getRatings()))
                .add(addedRating)
                .divide(BigDecimal.valueOf(productStatistics.getRatings() + 1), RoundingMode.UNNECESSARY);
        productStatistics.setRate(rate);
        productStatistics.setRatings(productStatistics.getRatings() + 1);
        product.setProductStatistics(productStatistics);
        return product;
    }

    public Product updateInWishList(Product product) {
        product.getProductStatistics().setInWishList(product.getProductStatistics().getInWishList() + 1);
        return product;
    }

    public Product delete(Product product) {
        product.setDeleted(new Deleted(true, LocalDateTime.now()));
        return product;
    }

    public Product addImages(Product product, List<String> imageNames, String bucketName) {
        product.setImages(new ProductImages(imageNames, bucketName));
        return product;
    }

}
