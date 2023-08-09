package sanity.nil.onlineshop.application.product.interfaces.interactors;

import sanity.nil.onlineshop.application.product.dto.statistics.ProductStatisticsDTO;
import sanity.nil.onlineshop.application.product.dto.statistics.UpdateProductStatisticsDTO;

public interface UpdateProductStatisticsInteractor {

    ProductStatisticsDTO addRating(UpdateProductStatisticsDTO updateStatisticsDTO);

    ProductStatisticsDTO addToWishList(UpdateProductStatisticsDTO updateStatisticsDTO);
}
