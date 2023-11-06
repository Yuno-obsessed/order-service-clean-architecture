package sanity.nil.order.order.infrastructure;

import sanity.nil.order.application.order.persistence.OrderDAO;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.infrastructure.database.models.AddressModel;
import sanity.nil.order.infrastructure.database.models.OrderModel;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.models.UserModel;
import sanity.nil.order.infrastructure.database.orm.mapper.AddressMapper;
import sanity.nil.order.infrastructure.database.orm.mapper.OrderMapper;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

import java.util.*;

import static sanity.nil.order.util.EntityGenerator.*;

public class OrderInMemoryDAO implements OrderDAO {

    private Map<UUID, OrderModel> storage = new HashMap<>();

    private static final ProductModel product1 = ProductMapper.convertEntityToModel(
            generateProduct(UUID.randomUUID(), "product1", 30.0, generateActiveDiscount(30)), null);
    private static final UserModel user = new UserModel(UUID.randomUUID(), "example@gmail.com", "wordpass");
    private static final AddressModel address = AddressMapper.convertEntityToModel(generateAddress(UUID.randomUUID()));

    @Override
    public Order create(Order order) {
        product1.setId(order.getProducts().get(0).getProductID());
        user.setId(order.getClientID());
        address.setId(order.getAddressID());
        List<ProductModel> products = new ArrayList<>();
        products.add(product1);
        OrderModel model = OrderMapper.entityToModel(order, address, user, products);
        storage.put(UUID.randomUUID(), model);
        return OrderMapper.modelToEntity(model);
    }

    @Override
    public List<OrderProduct> getProductsOfOrder(List<UUID> ids) {
        return null;
    }
}
