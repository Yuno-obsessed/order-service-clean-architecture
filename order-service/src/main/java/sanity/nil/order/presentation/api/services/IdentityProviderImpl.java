package sanity.nil.order.presentation.api.services;

import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;

public class IdentityProviderImpl implements IdentityProvider {

    private Identity identity;

    @Override
    public void holdIdentity(Identity identity) {
        this.identity = identity;
    }

    @Override
    public Identity getCurrentIdentity() {
        return identity;
    }
}
