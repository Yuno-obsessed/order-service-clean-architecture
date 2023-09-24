package sanity.nil.order.domain.order.aggregate;

import sanity.nil.order.domain.common.aggregate.BaseAggregate;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.events.OrderDeletedEvent;
import sanity.nil.order.domain.order.exceptions.OrderIsDeletedException;
import sanity.nil.order.domain.order.exceptions.OrderProductAlreadyIsContained;
import sanity.nil.order.domain.order.exceptions.OrderProductNotExists;
import sanity.nil.order.domain.order.exceptions.OrderWasDeliveredException;
import sanity.nil.order.domain.order.vo.OrderID;
import sanity.nil.order.domain.common.vo.Deleted;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Order extends BaseAggregate{

    private OrderID orderID;
    private UUID addressID;
    private UUID clientID;
    private List<OrderProduct> products;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private PaymentOption paymentOption;
    private Deleted deleted;
    private BigDecimal totalPrice;
    private boolean closed;

    public void preprocessOrder() {
        if (deleted.isDeleted()) {
            throw OrderIsDeletedException.throwEx(deleted.getDeletedAt());
        }

        if (orderStatus.equals(OrderStatus.DELIVERED)) {
            throw OrderWasDeliveredException.throwEx();
        }
    }

    public void addProduct(OrderProduct product) {
        this.preprocessOrder();
        if (this.products.stream()
                .anyMatch(p -> p.getProductID()
                        .equals(product.getProductID()))) {
            throw OrderProductAlreadyIsContained.throwEx(product.getProductID());
        }
    }

    public void removeProduct(OrderProduct product) {
        this.preprocessOrder();
        if (!products.contains(product)) {
            throw OrderProductNotExists.throwEx(product.getProductID());
        }
        this.products.remove(product);
    }

    public void updateStatus(OrderStatus status) {
        this.preprocessOrder();
        this.orderStatus = status;
        if (status.equals(OrderStatus.DELIVERED) || status.equals(OrderStatus.CANCELED)) {
            this.closed = true;
        }
    }

    public void deleteOrder() {
        this.preprocessOrder();
        this.deleted.setDeleted(true);
        this.deleted.setDeletedAt(LocalDateTime.now());
        this.recordEvent(new OrderDeletedEvent(this.orderID.getId()));
    }

    public Order(OrderID orderID, UUID addressID, UUID clientID, List<OrderProduct> products,
                 OrderStatus orderStatus, PaymentMethod paymentMethod, PaymentOption paymentOption) {
        this.orderID = orderID;
        this.addressID = addressID;
        this.clientID = clientID;
        this.products = products;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.paymentOption = paymentOption;
        this.deleted = new Deleted();
        this.closed = false;
    }


    public OrderID getOrderID() {
        return orderID;
    }

    public UUID getAddressID() {
        return addressID;
    }

    public UUID getClientID() {
        return clientID;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public Deleted getOrderInfo() {
        return deleted;
    }

    public List<UUID> getProductIDs() {
        return products.stream().map(OrderProduct::getProductID).collect(Collectors.toList());
    }

    public BigDecimal getTotalPrice() {
        return products.stream()
                .map(OrderProduct::getTotalPrice)
                .reduce(BigDecimal::add).get();
    }

    public Map<OrderProduct, Integer> getMatchingProducts(List<OrderProduct> productsToFindFrom, List<OrderProduct> productsToFind) {
        Map<OrderProduct, Integer> result = new HashMap<>();
        for (OrderProduct oldProduct : productsToFindFrom) {
            OrderProduct matchingProduct = productsToFind.stream()
                    .filter(product -> product.getProductID().equals(oldProduct.getProductID()))
                    .findFirst()
                    .orElse(null);
            if (matchingProduct != null) {
                int quantityInOld = matchingProduct.getQuantity();
                int quantityDiff = oldProduct.getQuantity() - quantityInOld;

                if (quantityDiff > 0) {
                    result.put(oldProduct, quantityDiff);
                }
            } else {
                result.put(oldProduct, oldProduct.getQuantity());
            }
        }
        return result;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setAddressID(UUID addressID) {
        this.addressID = addressID;
    }

}
