package sanity.nil.onlineshop.product.infrastructure;

import lombok.NoArgsConstructor;
import sanity.nil.common.application.dto.BaseFilters;
import sanity.nil.product.application.dto.query.ProductQueryDTO;
import sanity.nil.product.application.dto.query.ProductQueryFilters;
import sanity.nil.product.application.interfaces.persistence.ProductDAO;
import sanity.nil.product.application.interfaces.persistence.ProductReader;
import sanity.nil.product.domain.entity.Product;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

import java.util.*;

@NoArgsConstructor
public class ProductInMemoryDAOImpl implements ProductDAO, ProductReader {

    private Map<UUID, ProductModel> storage = new HashMap<>();

    @Override
    public Product createProduct(Product product, List<String> imageNames) {
        ProductModel model = ProductMapper.convertEntityToModel(product, null);
        storage.put(UUID.randomUUID(), model);
        return ProductMapper.convertModelToEntity(model);
    }

    @Override
    public Product updateProduct(Product product, List<String> imageNames) {
        ProductModel model = storage.remove(product.getProductId().getId());
        ProductModel newModel = ProductMapper.convertEntityToModel(product, null);
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
    public ProductQueryDTO getProductQueryByName(String name) {
        return storage.values()
                .stream()
                .filter(model -> model.getName().equals(name))
                .findFirst()
                .map(ProductMapper::convertModelToProductQueryDTO)
                .orElse(null);
    }

    @Override
    public List<ProductQueryDTO> getProductQueriesWithFilters(ProductQueryFilters filters) {
        return null;
    }
}
