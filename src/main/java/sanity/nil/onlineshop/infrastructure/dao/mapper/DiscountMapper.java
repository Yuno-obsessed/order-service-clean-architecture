package sanity.nil.onlineshop.infrastructure.dao.mapper;

import sanity.nil.onlineshop.application.product.dto.DiscountDTO;
import sanity.nil.onlineshop.domain.product.entity.Discount;
import sanity.nil.onlineshop.infrastructure.model.DiscountModel;

import static sanity.nil.onlineshop.domain.product.entity.Discount.DiscountType.getByDiscount;

public class DiscountMapper {

    public static Discount convertModelToEntity(DiscountModel model) {
        if (model != null) {
            return new Discount(getByDiscount(model.getDiscount()), model.getStartsAt(), model.getEndsAt(), model.isExpired());
        } else {
            return null;
        }
    }

    public static DiscountModel convertEntityToModel(Discount entity) {
        return new DiscountModel(entity.getDiscountType().getDiscount(),
                entity.getStartsAt(), entity.getEndsAt(), entity.isExpired());
    }

    public static DiscountDTO convertModelToDTO(DiscountModel model) {
        return new DiscountDTO(model.getDiscount(), model.getStartsAt(), model.getEndsAt(), model.isExpired());
    }
}
