package sanity.nil.tourservice.service;

import sanity.nil.tourservice.infrastructure.database.model.User;

import java.util.UUID;

public interface UserService extends BaseService<User, UUID>{

    boolean isEmailVerified(UUID id);


    User getByIdentifier(String identifier);
}
