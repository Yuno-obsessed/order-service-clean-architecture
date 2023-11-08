package sanity.nil.order.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.exceptions.AddressNotFoundException;
import sanity.nil.order.application.order.exceptions.UserNotFoundException;
import sanity.nil.order.application.order.persistence.OrderDAO;
import sanity.nil.order.application.order.persistence.OrderReader;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.infrastructure.database.models.OrderModel;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.orm.AddressORM;
import sanity.nil.order.infrastructure.database.orm.OrderORM;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.UserORM;
import sanity.nil.order.infrastructure.database.orm.mapper.OrderMapper;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDAO, OrderReader {

    private final ProductORM productORM;
    private final OrderORM orderORM;
    private final AddressORM addressORM;
    private final UserORM userORM;

    @Override
    public List<OrderProduct> getProductsOfOrder(List<UUID> ids) {
        List<ProductModel> products = productORM.getAllByIdIn(ids);
        return ProductMapper.convertModelsToOrderEntities(products);

    }

    @Override
    public Order create(Order order) {
        OrderModel createdOrder = orderORM.save(OrderMapper.entityToModel(order,
                addressORM.findById(order.getAddressID()).orElseThrow(() ->
                        AddressNotFoundException.throwEx(order.getAddressID())),
                userORM.findById(order.getClientID()).orElseThrow(() ->
                        UserNotFoundException.throwEx(order.getClientID())),
                productORM.getAllByIdIn(order.getProductIDs()))
        );

        return OrderMapper.modelToEntity(createdOrder);
    }
}
