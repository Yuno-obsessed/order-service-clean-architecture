package sanity.nil.common.application.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BaseFilters {

    public int limit;
    public int offset;
    public Order order;

    public enum Order {
        ASC,
        DESC,
        ;
    };

}
