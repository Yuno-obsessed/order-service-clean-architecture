package sanity.nil.order.infrastructure.database.orm.mapper;

import sanity.nil.order.application.common.domain.vo.Discount;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.vo.OrderID;
import sanity.nil.order.infrastructure.database.models.*;

import java.util.List;

import static sanity.nil.order.application.common.domain.vo.Discount.DiscountType.getByDiscount;

public class OrderMapper {

    public static OrderModel entityToModel(Order entity, AddressModel addressModel, UserModel userModel, List<ProductModel> products) {
        OrderModel model = new OrderModel();
        model.setId(entity.getOrderID().getId());
        model.setOrderStatus(entity.getOrderStatus().name());
        model.setAddress(addressModel);
        model.setUser(userModel);
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
        Order order = new Order(new OrderID(model.getId()), AddressMapper.convertModelToEntity(model.getAddress()),
                model.getUser().getId(), products.stream().map(e -> new OrderProduct(model.getId(),
                e.getName(), e.getPrice(), new Discount(getByDiscount(e.getDiscount()),
                e.getDiscountStart(), e.getDiscountEnd()), e.getQuantity())).toList(),
                OrderStatus.valueOf(model.getOrderStatus()),PaymentMethod.valueOf(model.getPaymentMethod()),
                PaymentOption.valueOf(model.getPaymentOption()));
        return order;
    }
}
