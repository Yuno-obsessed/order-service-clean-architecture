package sanity.nil.onlineshop.infrastructure.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.interfaces.ProductRepository;
import sanity.nil.onlineshop.domain.product.entity.Product;
import sanity.nil.onlineshop.application.product.exceptions.ProductNotFound;
import sanity.nil.onlineshop.infrastructure.dao.ProductDAO;
import sanity.nil.onlineshop.infrastructure.model.ProductModel;

import java.util.UUID;

import static sanity.nil.onlineshop.infrastructure.dao.mapper.ProductMapper.convertEntityToModel;
import static sanity.nil.onlineshop.infrastructure.dao.mapper.ProductMapper.convertModelToProductDTO;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductDAO productDAO;

    @Override
    public ProductDTO createProduct(Product entity) {
        ProductModel newModel = productDAO.save(convertEntityToModel(entity));
        return convertModelToProductDTO(newModel);
    }

    @Override
    public ProductDTO getByProductId(UUID id) {
        return convertModelToProductDTO(productDAO
                .findById(id)
                .orElseThrow(() ->
                        ProductNotFound.throwEx(id)
                )
        );
    }


    @Override
    @Transactional
    public ProductDTO updateProduct(Product entity) {
        ProductModel maybeModel = productDAO
                .findById(entity.getProductId().getId())
                .orElseThrow(() ->
                        ProductNotFound.throwEx(entity.getProductId().getId())
        );
        ProductModel model = productDAO.save(maybeModel
                .withName(entity.getName())
                .withDescription(entity.getDescription())
        );
        return convertModelToProductDTO(model);
    }

    @Override
    @Transactional
    public void deleteByProductId(UUID id) {
        productDAO.deleteById(id);
    }
}
