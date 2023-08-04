package sanity.nil.onlineshop.domain.product.entity.consts;

public enum ProductType {
    ELECTRONICS(0, "Electronics", "ELC"),
    COMPUTERS(1, "Computers", "CMP"),
    ARTS_CRAFTS(2, "Arts & Crafts", "ACR"),
    AUTOMOTIVE(3, "Automotive", "AUM"),
    BABY(4, "Baby", "BBY"),
    BEAUTY_PERSONAL_CARE(5,"Beauty & Personal care", "BPC"),
    WOMEN_FASHION(6, "Women's fashion", "WMF"),
    MEN_FASHION(7, "Men's fashion", "MNF"),
    GIRLS_FASHION(8, "Girls' fashion", "GLF"),
    BOYS_FASHION(9, "Boys' fashion", "BYF"),
    HEALTH_HOUSEHOLD(10, "Health & Household", "HTH"),
    HOME_KITCHEN(11, "Home & Kitchen", "HMK"),
    INDUSTRIAL_SCIENTIFIC(12, "Industrial & Scientific", "INS"),
    LUGGAGE(13, "Luggage", "LGG"),
    MOVIES_TELEVISION(14, "Movies & Television", "MVT"),
    PET_SUPPLIES(15, "Pet supplies", "PTS"),
    SOFTWARE(16, "Software", "SFW"),
    SPORTS_OUTDOORS(17, "Sports & Outdoors", "SPO"),
    TOOLS_HOME_IMPROVEMENT(18, "Tools & Home Improvement", "THI"),
    TOYS_GAMES(19, "Toys & Games", "TOG"),
    VIDEO_GAMES(20, "Video Games", "VDG")
    ;

    ProductType(Integer typeId, String type, String prefix) {
        this.typeId = typeId;
        this.type = type;
        this.prefix = prefix;
    }

    private Integer typeId;
    private String type;
    private String prefix;

    public static ProductType getByPrefix(String prefix) {
        for (ProductType productType : ProductType.values()) {
            if (productType.prefix.equals(prefix)) {
                return productType;
            }
        }
        throw new RuntimeException("No product type with prefix = " + prefix + " found.");
    }

    public static ProductType getByTypeId(Integer typeId) {
        for (ProductType productType : ProductType.values()) {
            if (productType.typeId.equals(typeId)) {
                return productType;
            }
        }
        throw new RuntimeException("No product type with typeId = " + typeId + " found.");
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getType() {
        return type;
    }

    public String getPrefix() {
        return prefix;
    }
}
