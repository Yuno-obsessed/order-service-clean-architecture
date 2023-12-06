package sanity.nil.order.application.order.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.query.GetAllOrdersQuery;

@RequiredArgsConstructor
public class OrderQueryService {

    public final GetAllOrdersQuery getAllOrdersQuery;
}
