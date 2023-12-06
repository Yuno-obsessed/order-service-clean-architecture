package sanity.nil.order.application.product.dto.query;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.common.dto.BaseFilters;

@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryFilters extends BaseFilters {

    public String productType;
    public String productSubtype;
    public Integer productPriceBelow;
    public Integer productPriceAbove;

    public ProductQueryFilters(Integer limit, Integer offset, String orderParam, String productType,
                               String productSubtype, Integer productPriceBelow, Integer productPriceAbove) {
        super(limit, offset, Order.fromString(orderParam));
        this.productType = productType;
        this.productSubtype = productSubtype;
        this.productPriceBelow = productPriceBelow;
        this.productPriceAbove = productPriceAbove;
    }
}
