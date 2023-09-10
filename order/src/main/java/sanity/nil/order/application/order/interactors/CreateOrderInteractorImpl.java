package sanity.nil.order.application.order.interactors;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.CreateOrderDTO;
import sanity.nil.order.application.order.interfaces.interactors.CreateOrderInteractor;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.product.interfaces.query.ProductReader;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.services.OrderService;

import java.util.List;

@RequiredArgsConstructor
public class CreateOrderInteractorImpl implements CreateOrderInteractor {

    private final ProductReader productReader;
    private final OrderDAO orderDAO;
    private final OrderService orderService;

    @Override
    public void create(CreateOrderDTO dto) {
        List<OrderProduct> orderProducts = productReader.getProductsByIds(dto.productIDs);
        Order order = orderService.create(dto.addressID, dto.userID, orderProducts, dto.paymentMethod, dto.paymentOption);
        orderDAO.create(order);
    }

}
