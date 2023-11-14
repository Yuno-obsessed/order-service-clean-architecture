package sanity.nil.order.infrastructure.database.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sanity.nil.order.application.common.application.dto.BaseFilters;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.exceptions.ProductIsDeleted;
import sanity.nil.order.application.product.exceptions.ProductNotFound;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO, ProductReader {

    private final ProductORM productORM;

    @Override
    public Product createProduct(Product entity) {
        ProductModel newModel = productORM.save(ProductMapper.convertEntityToModel(entity));
        return ProductMapper.convertModelToEntity(newModel);
    }

    @Override
    @Transactional
    public Product updateProduct(Product entity) {
        UUID id = entity.getProductId().getId();
        ProductModel maybeModel = productORM.findById(id).orElseThrow(
                () -> ProductNotFound.throwEx(id));
        if (maybeModel.isLogicallyDeleted()) {
            throw ProductIsDeleted.throwEx(id);
        }
        ProductModel updatedModel = productORM.save(ProductMapper.convertEntityToModel(entity));
        return ProductMapper.convertModelToEntity(updatedModel);
    }

    @Override
    @Transactional
    public void increaseQuantity(UUID id, int quantity) {
        productORM.increaseQuantity(id, quantity);
    }

    @Override
    @Transactional
    public void decreaseQuantity(UUID id, int quantity) {
        productORM.decreaseQuantity(id, quantity);
    }

    @Override
    public List<ProductQueryDTO> getProductsQueriesWithPagination(BaseFilters filters) {
        Pageable pageable = PageRequest.of(filters.offset, filters.limit);
        return ProductMapper.convertListOfModelsToProductQueryDTOs(
                productORM.findAllWithPagination(pageable, filters.order.toString()));
    }

    @Override
    public ProductQueryDTO getProductQueryById(UUID id) {
        ProductModel maybeModel = productORM.findById(id).orElseThrow(
                () -> ProductNotFound.throwEx(id));
        if (maybeModel.isLogicallyDeleted()) {
            throw ProductIsDeleted.throwEx(id);
        }
        return ProductMapper.convertModelToProductQueryDTO(maybeModel);
    }

    @Override
    public ProductQueryDTO getProductQueryByName(String name) {
        ProductModel maybeModel = productORM.getByName(name).orElseThrow(
                () -> ProductNotFound.throwEx(name));
        if (maybeModel.isLogicallyDeleted()) {
            throw ProductIsDeleted.throwEx(name);
        }
        return ProductMapper.convertModelToProductQueryDTO(maybeModel);
    }

    @Override
    public List<ProductQueryDTO> getProductQueriesWithFilters(ProductQueryFilters filters) {
        Pageable pageable = PageRequest.of(filters.offset, filters.limit);
        return ProductMapper.convertListOfModelsToProductQueryDTOs(
                productORM.findByFilters(filters, pageable));
    }

    @Override
    public Product getProductById(UUID id) {
        ProductModel maybeModel = productORM.findById(id).orElseThrow(
                () -> ProductNotFound.throwEx(id));
        if (maybeModel.isLogicallyDeleted()) {
            throw ProductIsDeleted.throwEx(id);
        }
        return ProductMapper.convertModelToEntity(maybeModel);
    }
}
