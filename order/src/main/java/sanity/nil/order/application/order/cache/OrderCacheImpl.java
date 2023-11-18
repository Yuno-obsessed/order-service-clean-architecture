package sanity.nil.order.application.order.cache;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.query.AddressQueryDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.ProductQueryDTO;
import sanity.nil.order.application.order.interfaces.cache.OrderCache;
import sanity.nil.order.application.order.interfaces.cache.OrderCacheDAO;
import sanity.nil.order.domain.order.events.OrderAddedProductEvent;
import sanity.nil.order.domain.order.events.OrderCreatedEvent;
import sanity.nil.order.domain.order.events.OrderDeletedEvent;
import sanity.nil.order.domain.order.events.OrderProductCreate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderCacheImpl implements OrderCache {

    private final OrderCacheDAO orderCacheDAO;

    @Override
    public void orderCreateEvent(OrderCreatedEvent event) {
        OrderQueryDTO orderDTO = new OrderQueryDTO();
        orderDTO.orderID = event.getId();
        orderDTO.userID = event.getClientID();
        String[] address = event.getAddress().split(",");
        orderDTO.address = new AddressQueryDTO(UUID.fromString(address[0]),
                address[1], address[2], address[3],
                Integer.parseInt(address[4]), address[5]);
        orderDTO.paymentMethod = event.getPaymentMethod();
        orderDTO.paymentOption = event.getPaymentOption();
        orderDTO.orderStatus = event.getOrderStatus();
        List<ProductQueryDTO> products = new ArrayList<>();
        for (OrderProductCreate productEvent : event.getProducts()) {
            ProductQueryDTO product = new ProductQueryDTO();
            product.productID = productEvent.getProductID();
            product.name = productEvent.getName();
            product.actualPrice = productEvent.getPrice();
            product.quantity = productEvent.getQuantity();
            products.add(product);
        }
        orderDTO.products = products;
        orderDTO.createdAt = event.getBaseEvent().getEventTimestamp();
        orderDTO.updatedAt = event.getBaseEvent().getEventTimestamp();

        orderCacheDAO.saveOrder(orderDTO);
    }

    @Override
    public void orderAddProductEvent(OrderAddedProductEvent event) {
        OrderQueryDTO orderDTO = orderCacheDAO.getOrder(event.getOrderID());
    }

    @Override
    public void orderDeleteEvent(OrderDeletedEvent event) {

    }
}
