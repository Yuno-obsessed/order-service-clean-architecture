package sanity.nil.order.application.order.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.interfaces.cache.OrderCacheReader;

import java.util.UUID;

@RequiredArgsConstructor
public class GetOrderByIDQuery {

    private final OrderCacheReader orderCacheReader;
    private final IdentityProvider identityProvider;

    public OrderQueryDTO handle(UUID id) {
        Identity identity = identityProvider.getCurrentIdentity();
        return orderCacheReader.getOrder(identity.userID, id);
    }
}
