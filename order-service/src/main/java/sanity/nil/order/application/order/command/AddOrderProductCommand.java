package sanity.nil.order.application.order.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.order.application.common.interfaces.storage.FileStorage;
import sanity.nil.order.application.common.relay.interfaces.persistence.OutboxDAO;
import sanity.nil.order.application.order.dto.command.AddOrderProductCommandDTO;
import sanity.nil.order.application.order.dto.response.AddedOrderProductDTO;
import sanity.nil.order.application.order.interfaces.persistence.OrderDAO;
import sanity.nil.order.application.order.interfaces.persistence.OrderReader;
import sanity.nil.order.application.product.interfaces.persistence.ProductImageReader;
import sanity.nil.order.domain.common.entity.Discount;
import sanity.nil.order.domain.order.aggregate.Order;
import sanity.nil.order.domain.order.entity.OrderProduct;
import sanity.nil.order.domain.order.entity.ProductImages;
import sanity.nil.order.domain.order.services.OrderService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class AddOrderProductCommand {

    private final OrderDAO orderDAO;
    private final OrderReader orderReader;
    private final OrderService orderService;
    private final OutboxDAO outboxDAO;
    private final FileStorage fileStorage;
    private final ProductImageReader productImageReader;
    private final IdentityProvider identityProvider;

    public AddedOrderProductDTO handle(AddOrderProductCommandDTO orderCommandDTO) {
        Identity identity = identityProvider.getCurrentIdentity();
        OrderProduct orderProduct = orderReader.getOrderProduct(orderCommandDTO.productID);
        Order order = orderReader.getOrderById(orderCommandDTO.orderID, identity.userID);
        order = orderService.addProduct(order, orderProduct);
        outboxDAO.addEvents(order.pullEvents());
        order = orderDAO.update(order);

        BigDecimal actualPrice = null;
        Discount discount = orderProduct.getDiscount();
        if (discount != null) {
             actualPrice = orderProduct.getPrice()
                    .subtract(orderProduct.getPrice()
                            .divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(orderProduct.getDiscount().getPercent())));
        }
        ProductImages productImages = productImageReader.getProductImagesByID(orderProduct.getId());
        String imageName;
        String imageURL = null;
        if (productImages != null) {
            imageName = productImages.getImageNames().stream().filter(e -> e.lastIndexOf('0') == e.length() - 1)
                    .findFirst()
                    .orElse(null);
            imageURL = fileStorage.getFileURL(imageName, productImages.getBucketName());
        }
        return new AddedOrderProductDTO(orderProduct.getId(), orderProduct.getName(), orderProduct.getTotalPrice(),
                discount == null ? null : discount.getPercent(), actualPrice, imageURL);
    }
}
