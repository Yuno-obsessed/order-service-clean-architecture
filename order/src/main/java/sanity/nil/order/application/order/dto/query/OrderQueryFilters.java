package sanity.nil.order.application.order.dto.query;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sanity.nil.order.application.common.dto.BaseFilters;

@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryFilters extends BaseFilters {

    public String withStatus;

    public OrderQueryFilters(Integer limit, Integer offset, String order, String withStatus) {
        super(limit, offset, Order.fromString(order));
        this.withStatus = withStatus;
    }

}
