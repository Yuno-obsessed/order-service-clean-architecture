package sanity.nil.order.infrastructure.database.orm;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sanity.nil.product.application.dto.query.ProductQueryFilters;
import sanity.nil.order.infrastructure.database.models.ProductModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductORM extends JpaRepository<ProductModel, UUID> {

    List<ProductModel> getAllByIdIn(List<UUID> ids);

    @Query(
            "SELECT p FROM ProductModel p " +
                    "WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :nameFilter, '%'))"
    )
    Optional<ProductModel> getByName(String name);

    @Query(
            "SELECT p FROM ProductModel p " +
                    "WHERE (:#{#filters.productSubtype} IS NULL OR p.productSubtype.subtypeName = :#{#filters.productSubtype}) " +
                    "AND (:#{#filters.productType} IS NULL OR p.productSubtype.productType.typeName = :#{#filters.productType}) " +
                    "ORDER BY CASE WHEN :#{#filters.order} = 'ASC' THEN 'p.id ASC' ELSE 'p.id DESC' END"
    )
    List<ProductModel> findByFilters(@Param("filters") ProductQueryFilters filters, Pageable pageable);

    @Query(
            "SELECT p FROM ProductModel p " +
                    "order by CASE WHEN :order = 'ASC' THEN 'p.id ASC' ELSE 'p.id DESC' END"
    )
    List<ProductModel> findAllWithPagination(Pageable pageable, String order);
}
