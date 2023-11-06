package sanity.nil.order.application.product.dto.query;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.common.application.dto.BaseFilters;

@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryFilters extends BaseFilters {

    public String productType;
    public String productSubtype;
    public Integer productPriceBelow;
    public Integer productPriceAbove;

    public ProductQueryFilters(int limit, int offset, Order order, String productType,
                               String productSubtype, Integer productPriceBelow, Integer productPriceAbove) {
        super(limit, offset, order);
        this.productType = productType;
        this.productSubtype = productSubtype;
        this.productPriceBelow = productPriceBelow;
        this.productPriceAbove = productPriceAbove;
    }
}
