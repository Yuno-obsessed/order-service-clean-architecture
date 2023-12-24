package sanity.nil.order.domain.order.aggregate;

import sanity.nil.order.domain.common.aggregate.BaseAggregate;
import sanity.nil.order.domain.common.vo.Deleted;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.events.OrderDeletedEvent;
import sanity.nil.order.domain.order.exceptions.*;
import sanity.nil.order.domain.order.vo.OrderID;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Order extends BaseAggregate{

    private OrderID orderID;
    private Address address;
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

        if (orderStatus.equals(OrderStatus.CANCELED)) {
            throw OrderIsCanceledException.throwEx();
        }
    }

    public void addProduct(OrderProduct product) {
        this.preprocessOrder();
        if (this.products.stream()
                .anyMatch(p -> p.getId()
                        .equals(product.getId()))) {
            throw OrderProductAlreadyIsContainedException.throwEx(product.getId());
        }
        this.products.add(product);
    }

    public void removeProduct(OrderProduct product) {
        this.preprocessOrder();
        if (!products.contains(product)) {
            throw OrderProductNotExistsException.throwEx(product.getId());
        }
        this.products.remove(product);
    }

    public void updateStatus(OrderStatus status) {
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

    public Order(OrderID orderID, Address address, UUID clientID, List<OrderProduct> products,
                 OrderStatus orderStatus, PaymentMethod paymentMethod, PaymentOption paymentOption) {
        this.orderID = orderID;
        this.address = address;
        this.clientID = clientID;
        this.products = products;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.paymentOption = paymentOption;
        this.deleted = new Deleted();
        this.closed = false;
        this.totalPrice = calculateTotalPrice();
    }

    public BigDecimal calculateTotalPrice() {
        return products.stream()
                .map(OrderProduct::getTotalPrice)
                .reduce(BigDecimal::add).get();
    }

    public Map<OrderProduct, Integer> getMatchingProducts(List<OrderProduct> productsToFindFrom, List<OrderProduct> productsToFind) {
        Map<OrderProduct, Integer> result = new HashMap<>();
        for (OrderProduct oldProduct : productsToFindFrom) {
            OrderProduct matchingProduct = productsToFind.stream()
                    .filter(product -> product.getId().equals(oldProduct.getId()))
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

    public OrderID getOrderID() {
        return orderID;
    }

    public Address getAddress() {
        return address;
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
        return products.stream().map(OrderProduct::getId).collect(Collectors.toList());
    }
    public boolean isClosed() {
        return closed;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID) &&
                Objects.equals(address, order.address) &&
                Objects.equals(clientID, order.clientID) &&
                Objects.equals(products, order.products) &&
                orderStatus == order.orderStatus &&
                paymentMethod == order.paymentMethod &&
                paymentOption == order.paymentOption &&
                Objects.equals(deleted, order.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, address, clientID, products, orderStatus, paymentMethod, paymentOption, deleted);
    }
}
