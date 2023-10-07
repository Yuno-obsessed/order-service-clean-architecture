package sanity.nil.product.domain.entity;

import sanity.nil.common.domain.vo.Deleted;
import sanity.nil.common.domain.vo.Discount;
import sanity.nil.product.domain.vo.ProductID;
import sanity.nil.product.domain.vo.ProductStatistics;
import sanity.nil.product.domain.vo.ProductSubtype;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private ProductID productId;
    private String description;
    private String name;
    private BigDecimal price;
    private Discount discount;
    private Integer quantity;
    private boolean availability;
    private Deleted deleted;
    private ProductSubtype productSubtype;
    private ProductStatistics productStatistics;

    public Product() {}

    public Product(ProductID productId, String description, String name, BigDecimal price, Discount discount,
                   Integer quantity, boolean availability, Deleted deleted, ProductSubtype productSubtype, ProductStatistics productStatistics) {
        this.productId = productId;
        this.description = description;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.availability = availability;
        this.deleted = deleted;
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

    public Integer getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setDeleted(Deleted deleted) {
        this.deleted = deleted;
    }

    public Deleted getDeleted() {
        return deleted;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(description, product.description) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(quantity, product.quantity) &&
                Objects.equals(deleted, product.deleted) &&
                Objects.equals(productSubtype, product.productSubtype) &&
                Objects.equals(productStatistics, product.productStatistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, description, name, price, discount, quantity, deleted, productSubtype, productStatistics);
    }

}
