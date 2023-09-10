package sanity.nil.order.domain.product.vo;

import java.math.BigDecimal;

public class ProductStatistics {

    private BigDecimal rate;
    private Integer ratings;
    private Integer inWishList;

    public ProductStatistics() {}

    public ProductStatistics(BigDecimal rate, Integer ratings, Integer inWishList) {
        this.rate = rate;
        this.ratings = ratings;
        this.inWishList = inWishList;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public Integer getInWishList() {
        return inWishList;
    }

    public void setInWishList(Integer inWishList) {
        this.inWishList = inWishList;
    }
}
