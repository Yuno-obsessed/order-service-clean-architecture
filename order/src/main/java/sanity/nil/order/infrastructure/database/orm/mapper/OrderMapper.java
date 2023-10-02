package sanity.nil.order.infrastructure.database.orm.mapper;

import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.consts.OrderStatus;
import sanity.nil.order.domain.order.consts.PaymentMethod;
import sanity.nil.order.domain.order.consts.PaymentOption;
import sanity.nil.order.domain.order.vo.OrderID;
import sanity.nil.order.infrastructure.database.models.AddressModel;
import sanity.nil.order.infrastructure.database.models.OrderModel;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.models.UserModel;

import java.util.List;

public class OrderMapper {

    public static OrderModel entityToModel(Order entity, AddressModel addressModel, UserModel userModel, List<ProductModel> products) {
        OrderModel model = new OrderModel();
        model.setId(entity.getOrderID().getId());
        model.setOrderStatus(entity.getOrderStatus().name());
        model.setAddress(addressModel);
        model.setUser(userModel);
        model.setProducts(products);
        model.setDeleted(entity.getOrderInfo().isDeleted());
        model.setDeletedAt(entity.getOrderInfo().getDeletedAt());
        model.setPaymentOption(entity.getPaymentOption().name());
        model.setPaymentMethod(entity.getPaymentMethod().name());
        return model;
    }

    public static Order modelToEntity(OrderModel model) {
        Order order = new Order(new OrderID(model.getId()), model.getAddress().getId(),
                model.getUser().getId(), ProductMapper.convertModelsToOrderEntities(model.getProducts()),
                OrderStatus.valueOf(model.getOrderStatus()),PaymentMethod.valueOf(model.getPaymentMethod()),
                PaymentOption.valueOf(model.getPaymentOption()));
        return order;
    }
}
