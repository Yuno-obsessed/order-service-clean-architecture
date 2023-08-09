package sanity.nil.onlineshop.domain.product.entity;


import lombok.Getter;
import sanity.nil.onlineshop.domain.product.vo.*;

import java.math.BigDecimal;

public class Product {

    private ProductID productId;
    private String description;
    private String name;
    private BigDecimal price;
    private Discount discount;
    private BigDecimal actualPrice;
    private Integer quantity;
    private boolean availability;
    private State state;
    private ProductSubtype productSubtype;
    private ProductStatistics productStatistics;

    public Product() {}

    public Product(ProductID productId, String description, String name, BigDecimal price, Discount discount,
                   BigDecimal actualPrice, Integer quantity, boolean availability, State state, ProductSubtype productSubtype) {
        this.productId = productId;
        this.description = description;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.actualPrice = actualPrice;
        this.quantity = quantity;
        this.availability = availability;
        this.state = state;
        this.productSubtype = productSubtype;
    }

    public Product(ProductID productId, String description, String name, BigDecimal price, Discount discount, BigDecimal actualPrice,
                   Integer quantity, boolean availability, State state, ProductSubtype productSubtype, ProductStatistics productStatistics) {
        this.productId = productId;
        this.description = description;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.actualPrice = actualPrice;
        this.quantity = quantity;
        this.availability = availability;
        this.state = state;
        this.productSubtype = productSubtype;
        this.productStatistics = productStatistics;
    }

    public ProductID getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public ProductSubtype getProductSubtype() {
        return productSubtype;
    }

    public void setProductStatistics(ProductStatistics productStatistics) {
        this.productStatistics = productStatistics;
    }

    public ProductStatistics getProductStatistics() {
        return productStatistics;
    }
}
