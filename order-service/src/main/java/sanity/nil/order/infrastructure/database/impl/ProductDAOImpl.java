package sanity.nil.order.infrastructure.database.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sanity.nil.order.application.common.dto.BaseFilters;
import sanity.nil.order.application.product.dto.query.ProductCardQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.exceptions.DiscountNotFound;
import sanity.nil.order.application.product.exceptions.ProductIsDeleted;
import sanity.nil.order.application.product.exceptions.ProductNotFound;
import sanity.nil.order.application.product.exceptions.SubtypeNotFound;
import sanity.nil.order.application.product.interfaces.persistence.*;
import sanity.nil.order.domain.common.entity.Discount;
import sanity.nil.order.domain.order.entity.ProductImages;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.entity.ProductSubtype;
import sanity.nil.order.infrastructure.database.models.DiscountModel;
import sanity.nil.order.infrastructure.database.models.ProductModel;
import sanity.nil.order.infrastructure.database.models.ProductSubtypeModel;
import sanity.nil.order.infrastructure.database.orm.DiscountORM;
import sanity.nil.order.infrastructure.database.orm.ProductImageORM;
import sanity.nil.order.infrastructure.database.orm.ProductORM;
import sanity.nil.order.infrastructure.database.orm.ProductSubtypeORM;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductSubtypeMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO, ProductReader, ProductSubtypeReader,
        DiscountReader, ProductImageReader {

    private final ProductORM productORM;
    private final ProductSubtypeORM productSubtypeORM;
    private final DiscountORM discountORM;
    private final ProductImageORM productImageORM;

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
    public List<ProductCardQueryDTO> getProductQueriesWithFilters(ProductQueryFilters filters) {
        Pageable pageable = PageRequest.of(filters.offset, filters.limit);
        filters.orderBy = "p." + filters.orderBy;
        return ProductMapper.convertListOfModelsToProductCardQueryDTOs(
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

    @Override
    public ProductSubtype getBySubtypeId(Integer id) {
        ProductSubtypeModel maybeProductSubtype = productSubtypeORM.findById(id).orElseThrow(() ->
                SubtypeNotFound.throwEx(id));
        return ProductSubtypeMapper.convertModelToEntity(maybeProductSubtype);
    }

    @Override
    public Discount getByID(UUID id) {
        DiscountModel maybeDiscountModel = discountORM.findById(id).orElseThrow(() ->
                DiscountNotFound.throwEx(id));
        return ProductMapper.convertDiscountModelToEntity(maybeDiscountModel);
    }

    @Override
    public ProductImages getProductImagesByID(UUID productID) {
        return ProductMapper.convertProductImagesModelToEntity(productImageORM.getAllByProductId(productID));
    }
}
