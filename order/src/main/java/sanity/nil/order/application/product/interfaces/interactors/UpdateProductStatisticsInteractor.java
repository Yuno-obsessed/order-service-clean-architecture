package sanity.nil.order.application.product.interfaces.interactors;

import sanity.nil.order.application.product.dto.statistics.ProductStatisticsDTO;
import sanity.nil.order.application.product.dto.statistics.UpdateProductStatisticsDTO;

public interface UpdateProductStatisticsInteractor {

    ProductStatisticsDTO addRating(UpdateProductStatisticsDTO updateStatisticsDTO);

    ProductStatisticsDTO addToWishList(UpdateProductStatisticsDTO updateStatisticsDTO);
}
