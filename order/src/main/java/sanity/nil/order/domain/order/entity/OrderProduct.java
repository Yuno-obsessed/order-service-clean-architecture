package sanity.nil.order.domain.order.entity;

import sanity.nil.order.domain.common.vo.Discount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class OrderProduct {

    private UUID productID;
    private String name;
    private BigDecimal price;
    private Discount discount;
    private int quantity;

    public OrderProduct(UUID productID, String name, BigDecimal price, Discount discount, Integer quantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }

    public UUID getProductID() {
        return productID;
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

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));
        BigDecimal priceAfterDiscount = totalPrice
                .subtract(totalPrice
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(discount.getDiscountInt())));
        return priceAfterDiscount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
