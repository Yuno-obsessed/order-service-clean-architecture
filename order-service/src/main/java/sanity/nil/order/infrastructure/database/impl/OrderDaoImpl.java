package sanity.nil.order.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.exceptions.AddressNotFoundException;
import sanity.nil.order.application.order.exceptions.OrderNotFoundException;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.order.interfaces.persistence.OrderReader;
import sanity.nil.order.application.product.exceptions.ProductNotFound;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.infrastructure.database.models.AddressModel;
import sanity.nil.order.infrastructure.database.models.OrderModel;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.orm.AddressORM;
import sanity.nil.order.infrastructure.database.orm.OrderORM;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.mapper.AddressMapper;
import sanity.nil.order.infrastructure.database.orm.mapper.OrderMapper;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDAO, OrderReader {

    private final ProductORM productORM;
    private final OrderORM orderORM;
    private final AddressORM addressORM;

    @Override
    public List<OrderProduct> getProductsOfOrder(List<UUID> ids) {
        List<ProductModel> products = productORM.getAllByIdIn(ids);
        return ProductMapper.convertModelsToOrderEntities(products);

    }

    @Override
    public OrderProduct getOrderProduct(UUID id) {
        ProductModel product = productORM.findById(id).orElseThrow(() ->
                ProductNotFound.throwEx(id));
        return ProductMapper.convertModelToOrderEntity(product);
    }

    @Override
    public Order getOrderById(UUID orderID, UUID userID) {
        OrderModel maybeModel = orderORM.findByIdAndUserID(orderID, userID).orElseThrow(() ->
                OrderNotFoundException.throwEx(orderID));
        List<ProductModel> products = productORM.getAllByIdIn(maybeModel.getProducts().stream()
                .map(e -> e.getProduct().getId()).toList());
        return OrderMapper.modelToEntity(maybeModel, products);
    }

    @Override
    public Address getAddress(UUID id) {
        AddressModel address = addressORM.findById(id).orElseThrow(() ->
                AddressNotFoundException.throwEx(id));
        return AddressMapper.convertModelToEntity(address);
    }

    @Override
    public Order create(Order order) {
        List<ProductModel> products = productORM.getAllByIdIn(order.getProductIDs());
        OrderModel createdOrder = orderORM.save(OrderMapper.entityToModel(order,
                addressORM.findById(order.getAddress().getAddressID().getId()).orElseThrow(() ->
                        AddressNotFoundException.throwEx(order.getAddress().getAddressID().getId())),
                products)
        );

        return OrderMapper.modelToEntity(createdOrder, products);
    }

    @Override
    public Order update(Order order) {
        UUID id = order.getOrderID().getId();
        OrderModel maybeModel = orderORM.findById(id).orElseThrow(() ->
                OrderNotFoundException.throwEx(id));
        List<ProductModel> products = productORM.getAllByIdIn(order.getProductIDs());
        OrderModel updatedModel = orderORM.save(OrderMapper.entityToModel(order,
                addressORM.findById(order.getAddress().getAddressID().getId()).orElseThrow(() ->
                        AddressNotFoundException.throwEx(order.getAddress().getAddressID().getId())),
                products));
        return OrderMapper.modelToEntity(updatedModel, products);
    }
}
