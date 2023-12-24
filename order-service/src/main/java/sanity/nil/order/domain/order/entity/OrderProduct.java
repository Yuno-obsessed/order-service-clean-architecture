package sanity.nil.order.domain.order.entity;

import sanity.nil.order.domain.common.entity.Discount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.UUID;

public class OrderProduct {

    private UUID id;
    private String name;
    private BigDecimal price;
    private Discount discount;
    private int quantity;

    public OrderProduct(UUID id, String name, BigDecimal price, Discount discount, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }

    public OrderProduct(UUID id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        int discountInt = 0;
        if (discount.isActive()) {
            discountInt = discount.getPercent();
        }
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));
        BigDecimal priceAfterDiscount = totalPrice
                .subtract(totalPrice
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(discountInt)));
        return priceAfterDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct product = (OrderProduct) o;
        return quantity == product.quantity &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(discount, product.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discount, quantity);
    }
}
