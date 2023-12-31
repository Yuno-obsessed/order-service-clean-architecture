package sanity.nil.order.product.infrastructure;

import lombok.NoArgsConstructor;
import sanity.nil.order.application.common.dto.BaseFilters;
import sanity.nil.order.application.product.dto.query.ProductCardQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

import java.util.*;

@NoArgsConstructor
public class ProductInMemoryDAOImpl implements ProductDAO, ProductReader {

    private Map<UUID, ProductModel> storage = new HashMap<>();

    @Override
    public Product createProduct(Product product) {
        ProductModel model = ProductMapper.convertEntityToModel(product);
        storage.put(UUID.randomUUID(), model);
        return ProductMapper.convertModelToEntity(model);
    }

    @Override
    public Product updateProduct(Product product) {
        ProductModel model = storage.remove(product.getProductId().getId());
        ProductModel newModel = ProductMapper.convertEntityToModel(product);
        storage.put(product.getProductId().getId(), newModel);
        return ProductMapper.convertModelToEntity(newModel);
    }

    @Override
    public Product getProductById(UUID id) {
        return ProductMapper.convertModelToEntity(storage.get(id));
    }

    @Override
    public List<ProductQueryDTO> getProductsQueriesWithPagination(BaseFilters filters) {
        List<ProductModel> models = new ArrayList<>(storage.values());
        if (BaseFilters.Order.ASC.equals(filters.order)) {
            models.sort(Comparator.comparing(ProductModel::getName));
        } else if (BaseFilters.Order.DESC.equals(filters.order)) {
            models.sort(Comparator.comparing(ProductModel::getName).reversed());
        }

        int startIndex = Math.min(filters.offset, models.size());
        int endIndex = Math.min(filters.offset + filters.limit, models.size());

        return ProductMapper.convertListOfModelsToProductQueryDTOs(models.subList(startIndex, endIndex));
    }

    @Override
    public ProductQueryDTO getProductQueryById(UUID id) {
        ProductModel model = storage.get(id);
        return ProductMapper.convertModelToProductQueryDTO(model);
    }

    @Override
    public List<ProductCardQueryDTO> getProductQueriesWithFilters(ProductQueryFilters filters) {
        return null;
    }

    @Override
    public void increaseQuantity(UUID id, int quantity) {

    }

    @Override
    public void decreaseQuantity(UUID id, int quantity) {

    }
}
