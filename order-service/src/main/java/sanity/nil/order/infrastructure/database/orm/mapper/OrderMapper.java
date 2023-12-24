package sanity.nil.order.infrastructure.database.orm.mapper;

import sanity.nil.order.domain.common.entity.Discount;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.vo.OrderID;
import sanity.nil.order.infrastructure.database.models.*;

import java.util.List;

public class OrderMapper {

    public static OrderModel entityToModel(Order entity, AddressModel addressModel, List<ProductModel> products) {
        OrderModel model = new OrderModel();
        model.setId(entity.getOrderID().getId());
        model.setOrderStatus(entity.getOrderStatus().name());
        model.setAddress(addressModel);
        model.setUserID(entity.getClientID());
        model.setProducts(productsToProductsRelation(products, model));
        model.setDeleted(entity.getOrderInfo().isDeleted());
        model.setDeletedAt(entity.getOrderInfo().getDeletedAt());
        model.setPaymentOption(entity.getPaymentOption().name());
        model.setPaymentMethod(entity.getPaymentMethod().name());
        return model;
    }

    private static List<OrderProductModel> productsToProductsRelation(List<ProductModel> products, OrderModel order) {
        return products.stream().map(e -> new OrderProductModel(order, e, e.getQuantity())).toList();
    }

    public static Order modelToEntity(OrderModel model, List<ProductModel> products) {
        return new Order(new OrderID(model.getId()), AddressMapper.convertModelToEntity(model.getAddress()),
                model.getUserID(), products.stream().map(e ->
        {
            Discount discount = null;
            if (e.getDiscount() != null) {
                DiscountModel discountModel = e.getDiscount();
                discount = new Discount(discountModel.getDiscountID(), discountModel.getPercent(),
                        discountModel.getStartedAt(), discountModel.getExpiredAt());
            }
            return new OrderProduct(model.getId(),
                    e.getName(), e.getPrice(), discount, e.getQuantity());
        }).toList(),
                OrderStatus.valueOf(model.getOrderStatus()),PaymentMethod.valueOf(model.getPaymentMethod()),
                PaymentOption.valueOf(model.getPaymentOption()));
    }
}
