package sanity.nil.order.application.order.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.query.GetAllOrdersQuery;
import sanity.nil.order.application.order.query.GetOrderByIDQuery;

@RequiredArgsConstructor
public class OrderQueryService {

    public final GetAllOrdersQuery getAllOrdersQuery;
    public final GetOrderByIDQuery getOrderByIDQuery;
}
