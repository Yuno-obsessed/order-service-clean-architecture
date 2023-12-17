package sanity.nil.library.services.interfaces;

import sanity.nil.library.services.data.Identity;

public interface IdentityProvider {

    void holdIdentity(Identity identity);

    Identity getCurrentIdentity();
}
