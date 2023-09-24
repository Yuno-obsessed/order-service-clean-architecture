package sanity.nil.order.infrastructure.database.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.product.dto.GetProductDTO;
import sanity.nil.order.application.product.dto.ProductDTO;
import sanity.nil.order.application.product.exceptions.ProductIsDeleted;
import sanity.nil.order.application.product.exceptions.ProductNotFound;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.application.product.interfaces.persistence.ProductReader;
import sanity.nil.order.domain.product.entity.Product;
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
    public List<Product> getProductsByIds(List<UUID> ids) {
        List<ProductModel> productModels = productORM.getAllByIdIn(ids);
        if (productModels.isEmpty()) {
            throw ProductNotFound.throwEx(ids);
        }
        return ProductMapper.convertModelsToEntities(productModels);
    }

    @Override
    public GetProductDTO getGETProductDTOById(UUID id) {
        ProductModel maybeModel = productORM.findById(id).orElseThrow(
                () -> ProductNotFound.throwEx(id));
        if (maybeModel.isLogicallyDeleted()) {
            throw ProductIsDeleted.throwEx(id);
        }
        return ProductMapper.convertModelToGetProductDTO(maybeModel);
    }

    @Override
    public ProductDTO getProductDTOById(UUID id) {
         ProductModel maybeModel = productORM.findById(id).orElseThrow(
                () -> ProductNotFound.throwEx(id));
        if (maybeModel.isLogicallyDeleted()) {
            throw ProductIsDeleted.throwEx(id);
        }
        return ProductMapper.convertModelToProductDTO(maybeModel);
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
    @Transactional
    public void deleteByProductId(UUID id) {
        productORM.deleteById(id);
    }
}
