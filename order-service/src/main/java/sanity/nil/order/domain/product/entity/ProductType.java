package sanity.nil.order.domain.product.entity;

public class ProductType {

    private String productTypeName;

    private String prefix;

    public ProductType(String productTypeName, String prefix) {
        this.productTypeName = productTypeName;
        this.prefix = prefix;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
