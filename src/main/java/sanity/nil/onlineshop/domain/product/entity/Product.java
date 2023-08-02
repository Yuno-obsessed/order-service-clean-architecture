package sanity.nil.onlineshop.domain.product.entity;


import sanity.nil.onlineshop.domain.product.vo.ProductID;

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
    // TODO:
//    private ProductType productType;
//
//    public enum ProductType {
//
//    }

    public Product(ProductID productId, String description, String name, BigDecimal price, Discount discount,
                   BigDecimal actualPrice, Integer quantity, boolean availability) {
        this.productId = productId;
        this.description = description;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.actualPrice = actualPrice;
        this.quantity = quantity;
        this.availability = availability;
    }

    public ProductID getProductId() {
        return productId;
    }

    public void setProductId(ProductID productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
