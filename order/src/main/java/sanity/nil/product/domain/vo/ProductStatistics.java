package sanity.nil.product.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStatistics that = (ProductStatistics) o;
        return Objects.equals(rate, that.rate) &&
                Objects.equals(ratings, that.ratings) &&
                Objects.equals(inWishList, that.inWishList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate, ratings, inWishList);
    }
}
