package sanity.nil.userservice.application.interfaces.persistence;

import sanity.nil.userservice.application.dto.query.UserQueryDTO;

import java.util.List;
import java.util.UUID;

public interface UserReader {

    List<UserQueryDTO> getUsers();

    UserQueryDTO getUserByID(UUID id);

    UserQueryDTO getUserByUsername(String username);

    UserQueryDTO getUserByEmail(String email);
}
