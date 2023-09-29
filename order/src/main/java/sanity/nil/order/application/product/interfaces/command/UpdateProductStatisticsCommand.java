package sanity.nil.order.application.product.interfaces.command;

import sanity.nil.order.application.product.dto.command.UpdateProductStatisticsDTO;
import sanity.nil.order.application.product.dto.query.ProductStatisticsQueryDTO;

public interface UpdateProductStatisticsCommand {

    ProductStatisticsQueryDTO addRating(UpdateProductStatisticsDTO updateStatisticsDTO);

    ProductStatisticsQueryDTO addToWishList(UpdateProductStatisticsDTO updateStatisticsDTO);
}
