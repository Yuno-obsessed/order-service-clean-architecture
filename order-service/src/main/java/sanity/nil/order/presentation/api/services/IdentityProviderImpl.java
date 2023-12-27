package sanity.nil.order.presentation.api.services;

import lombok.extern.slf4j.Slf4j;
import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;

@Slf4j
public class IdentityProviderImpl implements IdentityProvider {

    private Identity identity;

    @Override
    public void holdIdentity(Identity identity) {
        this.identity = identity;
    }

    @Override
    public Identity getCurrentIdentity() {
        log.info("Current user id: {}", identity.userID);
        return identity;
    }
}
