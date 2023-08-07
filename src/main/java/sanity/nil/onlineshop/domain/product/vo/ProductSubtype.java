package sanity.nil.onlineshop.domain.product.vo;

public class ProductSubtype {

    private Integer subtypeId;

    private String subtypeName;

    private String subtypePrefix;

    private ProductType productType;

    public ProductSubtype(Integer subtypeId, String subtypeName, String subtypePrefix, ProductType productType) {
        this.subtypeId = subtypeId;
        this.subtypeName = subtypeName;
        this.subtypePrefix = subtypePrefix;
        this.productType = productType;
    }

    public Integer getSubtypeId() {
        return subtypeId;
    }

    public void setSubtypeId(Integer subtypeId) {
        this.subtypeId = subtypeId;
    }

    public String getSubtypeName() {
        return subtypeName;
    }

    public void setSubtypeName(String subtypeName) {
        this.subtypeName = subtypeName;
    }

    public String getSubtypePrefix() {
        return subtypePrefix;
    }

    public void setSubtypePrefix(String subtypePrefix) {
        this.subtypePrefix = subtypePrefix;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}

