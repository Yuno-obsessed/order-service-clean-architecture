package sanity.nil.onlineshop.domain.product.vo;

import java.time.LocalDateTime;

public class Discount {

    public DiscountType discountType;
    public LocalDateTime startsAt;
    public LocalDateTime endsAt;
    public boolean expired;

    public enum DiscountType {
        THIRTY_PERCENT(0, 30),
        FIFTY_PERCENT(1, 50),
        BUY_TWO_PRICE_FOR_ONE(2, 0);

        private final Integer code;
        private final Integer discount;

        DiscountType(Integer code, Integer discount) {
            this.code = code;
            this.discount = discount;
        }

        public static DiscountType getByDiscount(Integer discount) {
            for (DiscountType enumValue : DiscountType.values()) {
                if (enumValue.discount.equals(discount)) {
                    return enumValue;
                }
            }
            throw new RuntimeException("No discount with discount = " + discount + "% found.");
        }

        public static DiscountType getByCode(Integer code) {
            for (DiscountType enumValue : DiscountType.values()) {
                if (enumValue.code.equals(code)) {
                    return enumValue;
                }
            }
            throw new RuntimeException("No discount with code = " + code + "% found.");
        }

        public Integer getDiscount() {
            return discount;
        }
    }

    public Discount(DiscountType discountType, LocalDateTime startsAt, LocalDateTime endsAt, boolean expired) {
        this.discountType = discountType;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.expired = expired;
    }

    public Discount(DiscountType discountType, LocalDateTime startsAt, LocalDateTime endsAt) {
        this.discountType = discountType;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.expired = isExpired();
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return startsAt.isBefore(now) && endsAt.isAfter(now);
    }
}
