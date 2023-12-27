package sanity.nil.order.application.order.cache;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.dto.query.AddressQueryDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.ProductQueryDTO;
import sanity.nil.order.application.order.interfaces.cache.OrderCache;
import sanity.nil.order.application.order.interfaces.cache.OrderCacheDAO;
import sanity.nil.order.domain.order.events.*;
import sanity.nil.order.domain.order.exceptions.ProductNotInOrderException;

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
        orderDTO.totalPrice = event.getTotalPrice();
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
        OrderQueryDTO orderDTO = orderCacheDAO.deleteOrder(event.getClientID(), event.uniqueAggregateID());
        ProductQueryDTO product = new ProductQueryDTO(
                event.getProductID(), event.getProductName(), event.getQuantity(), event.getPrice());
        orderDTO.products.add(product);
        orderDTO.totalPrice = orderDTO.totalPrice.add(product.actualPrice);
        orderDTO.updatedAt = event.getBaseEvent().getEventTimestamp();

        orderCacheDAO.saveOrder(orderDTO);
    }

    @Override
    public void orderRemoveProductEvent(OrderRemovedProductEvent event) {
        OrderQueryDTO orderDTO = orderCacheDAO.deleteOrder(event.getClientID(), event.uniqueAggregateID());
        orderDTO.products.removeIf(e -> e.productID.equals(event.getProductID()));
        orderDTO.totalPrice = orderDTO.totalPrice.subtract(event.getTotalPrice());
        orderDTO.updatedAt = event.getBaseEvent().getEventTimestamp();

        orderCacheDAO.saveOrder(orderDTO);
    }

    @Override
    public void orderUpdateProductQuantityEvent(OrderUpdatedProductQuantityEvent event) {
        OrderQueryDTO orderDTO = orderCacheDAO.deleteOrder(event.getClientID(), event.uniqueAggregateID());
        ProductQueryDTO productQueryDTO = orderDTO.products.stream()
                .findFirst()
                .filter(e -> e.productID.equals(event.getProductID())).orElseThrow(
                        () -> new ProductNotInOrderException(event.getProductID())
                );
        productQueryDTO.quantity = event.getQuantity();
        orderDTO.totalPrice = event.getUpdatedOrderPrice();
        orderDTO.updatedAt = event.getBaseEvent().getEventTimestamp();

        orderCacheDAO.saveOrder(orderDTO);
    }

    @Override
    public void orderUpdateAddressEvent(OrderUpdatedAddressEvent event) {
        OrderQueryDTO orderDTO = orderCacheDAO.deleteOrder(event.getClientID(), event.uniqueAggregateID());
        AddressQueryDTO addressQueryDTO = new AddressQueryDTO(
                event.getAddressID(), event.getCountry(), event.getCity(),
                event.getStreetName(), event.getBuildingNumber(), event.getPostalCode()
        );
        orderDTO.address = addressQueryDTO;
        orderDTO.updatedAt = event.getBaseEvent().getEventTimestamp();

        orderCacheDAO.saveOrder(orderDTO);
    }

    @Override
    public void orderDeleteEvent(OrderDeletedEvent event) {

    }
}
