package sanity.nil.order.infrastructure.database.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sanity.nil.common.application.dto.BaseFilters;
import sanity.nil.product.application.dto.query.ProductQueryDTO;
import sanity.nil.product.application.dto.query.ProductQueryFilters;
import sanity.nil.product.application.exceptions.ProductIsDeleted;
import sanity.nil.product.application.exceptions.ProductNotFound;
import sanity.nil.product.application.interfaces.persistence.ProductDAO;
import sanity.nil.product.application.interfaces.persistence.ProductReader;
import sanity.nil.product.domain.entity.Product;
import sanity.nil.order.infrastructure.database.models.ProductImageModel;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO, ProductReader {

    private final ProductORM productORM;

    @Override
    public Product createProduct(Product entity, List<String> imageNames) {
        List<ProductImageModel> imageModels = new ArrayList<>();
        for (String imageName : imageNames) {
            imageModels.add(new ProductImageModel(UUID.randomUUID(), imageName));
        }
        ProductModel newModel = productORM.save(ProductMapper.convertEntityToModel(entity, imageModels));
        return ProductMapper.convertModelToEntity(newModel);
    }

    @Override
    @Transactional
    public Product updateProduct(Product entity, List<String> imageNames) {
        UUID id = entity.getProductId().getId();
        ProductModel maybeModel = productORM.findById(id).orElseThrow(
                () -> ProductNotFound.throwEx(id));
        if (maybeModel.isLogicallyDeleted()) {
            throw ProductIsDeleted.throwEx(id);
        }
        List<ProductImageModel> imageModels = new ArrayList<>();
        if (imageNames != null) {
            for (String imageName : imageNames) {
                imageModels.add(new ProductImageModel(UUID.randomUUID(), imageName));
            }
        }
        ProductModel updatedModel = productORM.save(ProductMapper.convertEntityToModel(entity, imageModels));
        return ProductMapper.convertModelToEntity(updatedModel);
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
