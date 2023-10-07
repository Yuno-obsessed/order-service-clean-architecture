package sanity.nil.common.domain.vo;

import sanity.nil.common.application.exceptions.DiscountNotFoundException;

import java.time.LocalDateTime;
import java.util.Objects;

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
            throw DiscountNotFoundException.throwEx(discount, "discount percent");
        }

        public static DiscountType getByCode(Integer code) {
            for (DiscountType enumValue : DiscountType.values()) {
                if (enumValue.code.equals(code)) {
                    return enumValue;
                }
            }
            throw DiscountNotFoundException.throwEx(code, "code");
        }

        public Integer getDiscount() {
            return discount;
        }

        public Integer getCode() {
            return code;
        }
    }

    public Discount(DiscountType discountType, LocalDateTime startsAt, LocalDateTime endsAt) {
        this.discountType = discountType;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.expired = !isActive();
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

    public Integer getDiscountInt() {
        return discountType.getDiscount();
    }

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return startsAt.isBefore(now) && endsAt.isAfter(now);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return discountType == discount.discountType &&
                Objects.equals(expired, discount.expired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountType, startsAt, endsAt);
    }
}
